package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;


import controller.Controller;



@ManagedBean(name = "redeBean", eager = true)
@ApplicationScoped
public class RedeSocialCaronaBean{
	private String[] enderecosRecorrentes = {"Rua Dois", "Rua Três", "Rua Um", "Rua B", "Rua Quatro",
											"Rua Principal", "Rua A", "Rua C", "Rua Cinco", "Rua Seis", 
											"Rua D", "Rua Sete", "Rua Oito", "Rua E", "Rua F", "Rua Nove", "Rua Dez",
											"Rua G", "Rua São José", "Rua Onze", "Rua H", "Rua São Paulo", "Rua Doze", "Rua Treze",
											"Rua Santo Antônio (554)", "Avenida Brasil", "Rua I" , "Rua 2", "Rua 1",  "Rua 3"};
	
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
	public String geraNome(){
		int i = (int) (Math.random()*nomesComuns.length);
		int j = (int) (Math.random()*sobreNomesComuns.length);
		
		return nomesComuns[i] + " " + sobreNomesComuns[j];
	}
	
	public String geraEndereco(){
		int i = (int) (Math.random()*enderecosRecorrentes.length);
		return enderecosRecorrentes[i];
	}
	
	public RedeSocialCaronaBean(){
		criaUsuarios();
		criaCaronas();
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
		
		
	}
	
}
