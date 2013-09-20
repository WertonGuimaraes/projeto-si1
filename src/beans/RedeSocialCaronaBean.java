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
	
	private String[] nomesComuns = {"Gabriel", "Julia", "Giulia", "Arthur" , "Artur", "Sofia" , "Sophia",
									"Matheus", "Mateus",  "Maria Eduarda", "Davi", "David" , "Giovanna" , "Giovana",
									"Lucas",  "Isabela" ,"Isabella", "Guilherme", "Beatriz", "Pedro", "Manuela",  "Manoela",
									"Manuella", "Miguel", "Yasmin", "Iasmin", "Enzo",  "Maria Clara", "Gustavo" ,  "Ana Clara",
									"Mariana", "Felipe", "Filipe", "Gabriela", "Joao Pedro", "Luiza", "Luisa", "Pedro Henrique",
									"Laura", "Nicholas" , "Nicolas", "Lara", "Kaua" , "Caua",  "Nicole", "Terencio",
									"Tiaraju", "Jessika", "Rafael"}; 
	
	private String[] sobreNomesComuns = {"Silva", "Souza", "Costa", "Santos", "Oliveira", "Pereira", "Rodrigues",
										"Almeida", "Nascimento", "Lima", "Araujo", "Fernandes" , "Carvalho",
										"Gomes", "Martins", "Rocha", "Ribeiro", "Alves", "Monteiro", "Mendes", "Barros",
										"Freitas", "Barbosa", "Pinto", "Moura", "Cavalcanti", "Dias", "Castro", "Campos",
										"Cardoso", "Horto", "Smaneoto", "Rodrigues", "Rego"};
	/**
	 * Cria usuários e caronas default do sistema quando o sistema é iniciado.
	 */
	public RedeSocialCaronaBean(){
		criaUsuarios();
		criaCaronas();
		criaAmizades();
	}
	
	
	/**
	 * @return Nome aleatório, a partir das listas de nomes da classe.
	 */
	public String geraNome(){
		int i = (int) (Math.random()*nomesComuns.length);
		int j = (int) (Math.random()*sobreNomesComuns.length);
		
		return nomesComuns[i] + " " + sobreNomesComuns[j];
	}
	
	
	/**
	 * @return Endereço aleatório, a partir da lista de endereços da classe.
	 */
	public String geraEndereco(){
		int i = (int) (Math.random()*enderecosRecorrentes.length);
		return enderecosRecorrentes[i];
	}
	
	
	
	/**
	 * Cria 500 usuarios aleatórios
	 */
	private void criaUsuarios(){
		Controller.getInstance().criaConta("r", "r", "r", "r", "r");
		Controller.getInstance().criaConta("si1", "si1si1", "Sistemas de Informacao I", "si1@si1-ufcg.tk", "Aprigio Veloso....");
		
		for (int i = 0; i < 501; i++) {
			String login = "usuario"+i , senha = "si1", nome = geraNome();
			String email = "usuario"+i+"@si1-ufcg.tk", endereco = geraEndereco();
			Controller.getInstance().criaConta(login, senha, nome, email, endereco);
		}
		
	}
	
	
	/**
	 * Cria 500 caronas aleatórias
	 */
	private void criaCaronas(){
		for (int i = 0; i < 1000; i++) {
			Usuario usr = Controller.getInstance().searchPerfilUsuariobyLogin("usuario"+ (int)( Math.random()*500) );
			String data = ( 1+(int)(Math.random()*25) ) + "/" + ( 1+(int)(Math.random()*10) ) + "/" + 2014;
			String hora = String.valueOf((int)(Math.random()*23)) + ":00";
			String vagas = String.valueOf(1+(int)( Math.random()*5) );
			
			usr.adicionaCarona(geraEndereco(), geraEndereco(), data, hora, vagas);
		}
		
	}
	
	/**
	 * Cria amizades entre os usuarios dado que já existem 500 usuarios no sistema
	 */
	private void criaAmizades(){
		Usuario si1 = Controller.getInstance().searchUsuariobyLogin("si1");
		Usuario usr1 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
		Usuario usr2 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
		Usuario usr3 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
		Usuario usr4 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
		si1.addFriend(usr1);si1.addFriend(usr2);si1.addFriend(usr3);si1.addFriend(usr4);
		
		for (int i = 0; i < 2000; i++) {
			Usuario usr5 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
			Usuario usr6 = Controller.getInstance().searchUsuariobyLogin("usuario"+(int)(Math.random()*500));
			usr5.addFriend(usr6);
		}
	}
	
}
