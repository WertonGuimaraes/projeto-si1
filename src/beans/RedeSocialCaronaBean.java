package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;






import model.Usuario;
import controller.Controller;



@ManagedBean(name = "redeBean", eager = true)
@ApplicationScoped
public class RedeSocialCaronaBean{
	private String[] enderecosRecorrentes = {"Rua Dois", "Rua Tres", "Rua Um", "Rua B", "Rua Quatro",
											"Rua Principal", "Rua A", "Rua C", "Rua Cinco", "Rua Seis", 
											"Rua D", "Rua Sete", "Rua Oito", "Rua E", "Rua F", "Rua Nove", "Rua Dez",
											"Rua G", "Rua Sao Jose", "Rua Onze", "Rua H", "Rua Sao Paulo", "Rua Doze", "Rua Treze",
											"Rua Santo Antonio", "Avenida Brasil", "Rua I" , "Rua 2", "Rua 1",  "Rua 3"};
	
	private String[] nomesComuns = {"Gabriel", "Júlia", "Giulia", "Arthur" , "Artur", "Sofia" , "Sophia",
									"Matheus", "Mateus",  "Maria Eduarda", "Davi", "David" , "Giovanna" , "Giovana",
									"Lucas",  "Isabela" ,"Isabella", "Guilherme", "Beatriz", "Pedro", "Manuela",  "Manoela",
									"Manuella", "Miguel", "Yasmin", "Iasmin", "Enzo",  "Maria Clara", "Gustavo" ,  "Ana Clara",
									"Mariana", "Felipe", "Filipe", "Gabriela", "João Pedro", "Luiza", "Luísa", "Pedro Henrique",
									"Laura", "Nicholas" , "Nicolas", "Lara", "Kauã" , "Cauã",  "Nicole", "Terêncio",
									"Tiaraju", "Jessika", "Rafael"}; 
	
	private String[] sobreNomesComuns = {"Silva", "Souza", "Costa", "Santos", "Oliveira", "Pereira", "Rodrigues",
										"Almeida", "Nascimento", "Lima", "Araújo", "Fernandes" , "Carvalho",
										"Gomes", "Martins", "Rocha", "Ribeiro", "Alves", "Monteiro", "Mendes", "Barros",
										"Freitas", "Barbosa", "Pinto", "Moura", "Cavalcanti", "Dias", "Castro", "Campos",
										"Cardoso", "Horto", "Smaneoto", "Rodrigues", "Rego"};
	public RedeSocialCaronaBean(){
		criaUsuarios();
		criaCaronas();
	}
	
	
	public String geraNome(){
		int i = (int) (Math.random()*nomesComuns.length);
		int j = (int) (Math.random()*sobreNomesComuns.length);
		
		return nomesComuns[i] + " " + sobreNomesComuns[j];
	}
	
	public String geraEndereco(){
		int i = (int) (Math.random()*enderecosRecorrentes.length);
		return enderecosRecorrentes[i];
	}
	
	
	
	private void criaUsuarios(){
		Controller.getInstance().criaConta("r", "r", "r", "r", "r");
		Controller.getInstance().criaConta("si1", "si1si1", "Sistemas de Informação I", "si1@si1-ufcg.tk", "Aprígio Veloso....");
		
		for (int i = 0; i < 501; i++) {
			String login = "usuario"+i , senha = "si1", nome = geraNome();
			String email = "usuario"+i+"@si1-ufcg.tk", endereco = geraEndereco();
			Controller.getInstance().criaConta(login, senha, nome, email, endereco);
		}
		
	}
	
	private void criaCaronas(){
		for (int i = 0; i < 1000; i++) {
			Usuario usr = Controller.getInstance().searchPerfilUsuariobyLogin("usuario"+ (int)( Math.random()*500) );
			String data = ( 1+(int)(Math.random()*25) ) + "/" + ( 1+(int)(Math.random()*10) ) + "/" + 2014;
			String hora = String.valueOf((int)(Math.random()*23)) + ":00";
			String vagas = String.valueOf(1+(int)( Math.random()*5) );
			
			usr.adicionaCarona(geraEndereco(), geraEndereco(), data, hora, vagas);
		}
		
	}
	
}
