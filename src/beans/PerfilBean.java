package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import model.Carona;
import model.CaronaSolicitada;
import model.RequestMeetingPoint;
import model.ResponseMeetingPoint;
import model.SolicitacaoPontoEncontro;
import model.Usuario;
import controller.Controller;
import controller.SessionController;

/**
 * @author Rafael
 *
 */
/**
 * @author Rafael
 *
 */
@ViewScoped
@ManagedBean(name = "perfilBean")
public class PerfilBean {

	public static String ID;
	private String id;
	private String origem;
	private String destino;
	private String data;
	private String hora;
	private String vagas;
	private String pontoDeEncontro;
	private Carona carona;
	private String LoginDoUsuarioProcurado;
	private String opcao;
	private String pontosMotorista;
	int size;

	private Usuario usuario;
	private CaronaSolicitada selectedCaronaSolicitada;
	private Carona selectedCarona;
	private RequestMeetingPoint requestPoint;
	private ResponseMeetingPoint responsePoint;
	
	private List<Carona> caronasDisponiveis;
	private List<CaronaSolicitada> caronasSolicitadas;
	private List<CaronaSolicitada> caronasSolicitadasPorMim;
	private List<RequestMeetingPoint> requestMeetingPoint;
	private List<RequestMeetingPoint> requestsPontosEncontro;
	private List<ResponseMeetingPoint> responsesPontosEncontro;
	

	/**
	 * Procura o Usuario pela sua Id e monta o Perfil a partir do objeto
	 * Usuario.
	 */
	public PerfilBean() {
		if (ID != null) {
			this.usuario = SessionController.getInstance().searchSessionById(ID);
			this.id = ID;
			this.caronasDisponiveis = new ArrayList<Carona>();
			this.reset();
			localizaCaronasDisponiveis();
			update();
		} else
			throw new RuntimeException("Erro no id do perfil");
	}

	/**
	 * Faz logoff do usuario
	 * @return pagina inicial
	 */
	public String logoffButton() {
		SessionController.getInstance().desconectarSessao(Integer.parseInt(id)); 
		return "index.xhtml";
	}

	public void setSize(int size) {
		this.size = size;
	}



	public Usuario getUsuario() {
		return usuario;
	}

	public String getLoginDoUsuarioProcurado() {
		return LoginDoUsuarioProcurado;
	}
	
	/**
	 * Localiza um perfil de Usuario a partir do seu Login
	 * @return pagina de Perfil do Usuario solicitado
	 */
	public String localizaPerfil() {
		Usuario user = Controller.getInstance().searchUsuariobyLogin(LoginDoUsuarioProcurado);
		if(user != null){
			ShowerProfileBean.ID=String.valueOf(Controller.getInstance().visualizaPerfil(user));
			return "perfilViewer?faces-redirect=true";
		}
		
		throw new RuntimeException("perfil invalido");
	}
	
	/**
	 * Localiza o perfil do Próprio usuario a partir do seu login
	 * @return pagina de perfil
	 */
	public String meuPerfil(){
		Usuario user = Controller.getInstance().searchUsuariobyLogin(usuario.getLogin());
		if(user != null){
			ShowerProfileBean.ID=String.valueOf(Controller.getInstance().visualizaPerfil(user));
			return "perfilViewer?faces-redirect=true";
		}
		throw new RuntimeException("perfil invalido");
	}
	
	
	public void setLoginDoUsuarioProcurado(String login) {
		this.LoginDoUsuarioProcurado = login;
	}
	
	
	/**
	 * @return pagina de perfil
	 */
	public String redirectPerfil() {
		return "perfil.xhtml";
	}

	/**
	 * Limpa as listas de caronas e retorna para a página de perfil
	 * @return pagina do perfil
	 */
	public String cleanAndExit() {
		this.caronasDisponiveis = new ArrayList<Carona>();
		return "perfil.xhtml";

	}


	/**
	 * Localiza uma carona de acordo com a origem e destino especificados
	 */
	public void localizaCaronasDisponiveis() {
		this.setCaronasDisponiveis(new ArrayList<Carona>());
		List<Integer> idsCaronas = Controller.getInstance().buscaCarona(
				this.origem, this.destino);
		for (Integer id : idsCaronas) {
			this.caronasDisponiveis.add(Controller.getInstance()
					.buscaCarona(id));
		}

	}


	/**
	 * Cadastra uma carona a partir da orige,destino,data,hora e vagas setados no PerfilBean
	 */
	public void cadastrarCarona() {
		try {
			usuario.adicionaCarona(this.origem, this.destino, this.data,
					this.hora, this.vagas);
		} catch (Exception e) {
			msg(e.getMessage());
			return;
		}
		msg("Carona cadastrada com sucesso");
		this.reset();
		localizaCaronasDisponiveis();
		update();
	}

	/**
	 * Solicita uma carona e redireciona o usuario para o seu perfil
	 * @return perfil do usuario
	 */
	public String solicitarCarona() {

		this.usuario.solicitaVaga(this.selectedCarona);
		update();
		return redirectPerfil();
	}

	
	/**
	 * Atualiza todas as informações de PerfilBean.
	 */
	private void update() {
		Map<Integer, CaronaSolicitada> requisicoes = this.usuario.getRequests();
		caronasSolicitadas = new ArrayList<CaronaSolicitada>();
		for (Integer id : requisicoes.keySet()) {
			caronasSolicitadas.add(requisicoes.get(id));
		}

		this.setRequestMeetingPoint(this.usuario.getRequisicoesPontosPendentes());
		this.requestsPontosEncontro = this.usuario.getRequisicoesPontosPendentes();
		this.setResponsesPontosEncontro(this.usuario.getResponsesPontosPendentes());
		this.caronasSolicitadasPorMim = this.usuario.getCaronasSolicitadas();
	}

	/**
	 * Zera os atributos relacionados a Carona no PerfilBean. (origem,destino,data,hora,vagas)
	 */
	public void reset() {
		this.setOrigem("");
		this.setDestino("");
		this.setData("");
		this.setHora("");
		this.setVagas("");
	}

	/**
	 * Realiza upload de uma foto de perfil para o usuario
	 * @param event upload da foto
	 */
	public void upload(FileUploadEvent event) {  
		FacesMessage msg = new FacesMessage(event.getFile().getFileName() + " foi enviado com sucesso.");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
		// Do what you want with the file          
		System.out.println(1);
		try {  
			byte[] foto = event.getFile().getContents();  
			String nomeArquivo = event.getFile().getFileName();    
			System.out.println(2);
			FacesContext facesContext = FacesContext.getCurrentInstance();    
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();    
			String arquivo = scontext.getRealPath("/uploads/imagensTopo/" + nomeArquivo);  
			System.out.println(3);  
			//            String arquivo = scontext.getContextPath()+"/uploadis/" + nomeArquivo;  
			File f=new File(arquivo);  
			if(!f.getParentFile().exists()) f.getParentFile().mkdirs();  
			if(!f.exists()) f.createNewFile();  
			System.out.println("cheguei aqui");
			System.out.println(f.getAbsolutePath());  

			FileOutputStream fos=new FileOutputStream(arquivo);  
			fos.write(foto);  
			System.out.println(4);
			fos.flush();  
			System.out.println(5);
			fos.close();  
		} catch (IOException e) {  
			System.out.println("exececao");
			e.printStackTrace();  
		}  

	}  
	
	public Collection<Carona> getCaronas() {
		return usuario.getCaronas().values();
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Carona> getCaronasDisponiveis() {
		return this.caronasDisponiveis;
	}

	public void setCaronasDisponiveis(List<Carona> caronasDisponiveis) {
		this.caronasDisponiveis = caronasDisponiveis;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public void msg(String text) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage(text));
	}

	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	public Carona getCarona() {
		return carona;
	}

	public void setCarona(Carona carona) {
		this.carona = carona;
	}

	public List<CaronaSolicitada> getCaronasSolicitadas() {
		return caronasSolicitadas;
	}

	public void setCaronasSolicitadas(List<CaronaSolicitada> caronasSolicitadas) {
		this.caronasSolicitadas = caronasSolicitadas;
	}

	
	/**
	 * @return the selectedCarona
	 */
	public Carona getSelectedCarona() {
		return selectedCarona;
	}


	/**
	 * @param selectedCarona the selectedCarona to set
	 */
	public void setSelectedCarona(Carona selectedCarona) {
		
		this.selectedCarona = selectedCarona;
	}


	/**
	 * @return the requestMeetingPoint
	 */
	public List<RequestMeetingPoint> getRequestMeetingPoint() {
		return requestMeetingPoint;
	}


	/**
	 * @param requestMeetingPoint the requestMeetingPoint to set
	 */
	public void setRequestMeetingPoint(List<RequestMeetingPoint> requestMeetingPoint) {
		this.requestMeetingPoint = requestMeetingPoint;
	}

	/**
	 * @return the selectedCaronaSolictada
	 */
	public CaronaSolicitada getSelectedCaronaSolicitada() {
		return selectedCaronaSolicitada;
	}

	/**
	 * @param selectedCaronaSolictada the selectedCaronaSolictada to set
	 */
	public void setSelectedCaronaSolicitada(CaronaSolicitada selectedCaronaSolictada) {
		System.out.println("ta mudando");
		this.selectedCaronaSolicitada = selectedCaronaSolictada;
		System.out.println(this.selectedCaronaSolicitada.getOrigem());
	}
	
	/**
	 * Decide se aceita ou rejeita a Carona. 
	 * Os inteiros 1 e 2 aceitam e rejeitam, respectivamente, a carona.
	 * @return perfil do usuario
	 */
	public String escolheResultado(){
		if(opcao.equals("1")) aceitaCarona();
		else if(opcao.equals("2")) rejeitaCarona();
		
		update();
		return redirectPerfil();
	}
	
	/**
	 * Aceita uma solicitação de carona
	 */
	public void aceitaCarona(){
		Usuario motorista =  this.selectedCaronaSolicitada.getMotorista();
		int idCarona = this.selectedCaronaSolicitada.getId();
		
		motorista.aceitaRequest(idCarona);
		System.out.println("aceitou");
	}

	/***
	 * Rejeita a solicitação de carona
	 */
	public void rejeitaCarona(){
		Usuario motorista =  this.selectedCaronaSolicitada.getMotorista();
		int idCarona = this.selectedCaronaSolicitada.getId();
		
		motorista.rejeitarRequest(idCarona);
		
	}
	
	
	/**
	 * @return the opcao
	 */
	public String getOpcao() {
		return opcao;
	}

	/**
	 * @param opcao the opcao to set
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}


	/**
	 * @return the requestsPontosEncontro
	 */
	public List<RequestMeetingPoint> getRequestsPontosEncontro() {
		return requestsPontosEncontro;
	}

	/**
	 * @param requestsPontosEncontro the requestsPontosEncontro to set
	 */
	public void setRequestsPontosEncontro(List<RequestMeetingPoint> requestsPontosEncontro) {
		this.requestsPontosEncontro = requestsPontosEncontro;
	}
	
	/**
	 * Sugere ponto de encontro
	 */
	public String sugerirPonto(){
		Controller cont = Controller.getInstance();
		int idSessao = cont.getSessoes().searchSessionByLogin(this.usuario.getLogin());

		cont.sugerirPontoEncontro(String.valueOf(idSessao), 
				this.selectedCarona.getId(), this.pontoDeEncontro);
		update();
		msg("Ponto sugeiro com sucesso");
	
		return redirectPerfil();
	}

	/**
	 * @return the requestPoint
	 */
	public RequestMeetingPoint getRequestPoint() {
		return requestPoint;
	}

	/**
	 * @param requestPoint the requestPoint to set
	 */
	public void setRequestPoint(RequestMeetingPoint requestPoint) {
		this.requestPoint = requestPoint;
	}
	
	
	/**
	 * Escolhe se quer aceitar ou rejeitar uma carona e
	 * redireciona para o perfil do usuario
	 * @return pagina de perfil do usuario
	 */
	public String escolheResultadoRequest(){
		int idCarona = this.requestPoint.getIdCarona(); //id da carona mesmo
		this.selectedCarona = Controller.getInstance().buscaCarona(idCarona);
		//this.selectedCaronaSolicitada = Controller.getInstance().buscaCaronaSolicitada(idCaronaSolicitada)
		
		if(opcao.equals("1")) desistirCarona();
		else if(opcao.equals("2")) 	solicitarCarona();
		
		update();
		return redirectPerfil();
	}
	
	
	public void desistirCarona() {
		//CaronaSolicitada c
		//aqui eu preciso da CaronaSolicatda mas em RequestPoint eu so tenho o id da Carona normal
		//Controller.getInstance().desitirCarona(idSessao, idCarona, idSugestao);
	}
	
	
	/**
	 * Escolhe se aceita ou não uma Carona solicitada por outro usuario.
	 * Em seguida, redireciona para a pagina de perfil.
	 * @return pagina de perfil do usuario.
	 */
	public String escolheResultadoMinhasSolicitacoes(){
		int idSessao = Controller.getInstance().getSessoes().searchSessionByLogin(this.usuario.getLogin());
		System.out.println("verificando");
		System.out.println(this.opcao);
		if(opcao.equals("1")) desistirCarona(idSessao, this.selectedCaronaSolicitada.getId()); //idCarona inutil por isso 0 default
		
		update();
		return redirectPerfil();
	}
	
	/***
	 * 
	 * @param idSessao
	 * @param idSugestao e o id da carona solicitada 
	 */
	public void desistirCarona(int idSessao, int idSugestao){
		System.out.println("entrou");
		Controller.getInstance().desitirCarona(idSessao, idSugestao);
	}
	
	public String escolheResultadoResponse(){
		Controller cont = Controller.getInstance();
		Carona car = this.selectedCaronaSolicitada.getCarona();
		if(opcao.equals("1")){			
			Controller.getInstance().respondeSolicitacaoMeetingPoint(cont.getSessoes()
					.searchSessionByLogin(this.usuario.getLogin()), car.getId(),
					this.selectedCaronaSolicitada.getId(), this.pontosMotorista);
		}else if(opcao.equals("2")){
			
		}
		
		return redirectPerfil();
			
	}

	/**
	 * @return the caronasSolicitadasPorMim
	 */
	public List<CaronaSolicitada> getCaronasSolicitadasPorMim() {
		return caronasSolicitadasPorMim;
	}

	/**
	 * @param caronasSolicitadasPorMim the caronasSolicitadasPorMim to set
	 */
	public void setCaronasSolicitadasPorMim(List<CaronaSolicitada> caronasSolicitadasPorMim) {
		this.caronasSolicitadasPorMim = caronasSolicitadasPorMim;
	}

	/**
	 * @return the responsePoint
	 */
	public ResponseMeetingPoint getResponsePoint() {
		return responsePoint;
	}

	/**
	 * @param responsePoint the responsePoint to set
	 */
	public void setResponsePoint(ResponseMeetingPoint responsePoint) {
		this.responsePoint = responsePoint;
	}

	/**
	 * @return the pontosMotorista
	 */
	public String getPontosMotorista() {
		return pontosMotorista;
	}

	/**
	 * @param pontosMotorista the pontosMotorista to set
	 */
	public void setPontosMotorista(String pontosMotorista) {
		this.pontosMotorista = pontosMotorista;
	}

	/**
	 * @return the responsesPontosEncontro
	 */
	public List<ResponseMeetingPoint> getResponsesPontosEncontro() {
		return responsesPontosEncontro;
	}

	/**
	 * @param responsesPontosEncontro the responsesPontosEncontro to set
	 */
	public void setResponsesPontosEncontro(List<ResponseMeetingPoint> responsesPontosEncontro) {
		this.responsesPontosEncontro = responsesPontosEncontro;
	}
}
