package model;

import java.security.InvalidParameterException;

import org.joda.time.DateTime;

public class Carona {

	private String origem;
	private String destino;
	private DateTime dateTime;
	private int vagas,year,month,day,hour,minute;



	public Carona(String origem, String destino, String data,String horaSaida, int vagas) throws Exception{
		if(Util.isEmpty(origem) || Util.isEmpty(origem)|| Util.isEmpty(origem) || Util.isEmpty(origem) || vagas<1){
			throw new InvalidParameterException("Parametros incosistentes");
		}
		year = Integer.parseInt(data.split("/")[2]);
		month = Integer.parseInt(data.split("/")[1]);
		day = Integer.parseInt(data.split("/")[0]);
		hour =Integer.parseInt(horaSaida.split(":")[0]);
		minute = Integer.parseInt(horaSaida.split(":")[1]);

		this.dateTime = new DateTime(year, month, day, hour, minute);
		this.origem = origem;
		this.vagas=vagas;
		this.destino = destino;
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


	public String getDate(){
		return dateTime.toString("dd/MM/yyyy");
	}


	public String getHour() {
		return dateTime.getHourOfDay()+":"+dateTime.getMinuteOfHour();
	}

	public void setDate(String data) {
		this.year = Integer.parseInt(data.split("/")[2]);
		this.month = Integer.parseInt(data.split("/")[1]);
		this.day = Integer.parseInt(data.split("/")[0]);
		this.dateTime = new DateTime(year, month, day, hour, minute);
	}

	public void setHour(String hora){
		this.hour =Integer.parseInt(hora.split(":")[0]);
		this.minute = Integer.parseInt(hora.split(":")[1]);
		this.dateTime = new DateTime(year, month, day, hour, minute);
	}


	public int getVagas() {
		return vagas;
	}



	public void setVagas(int vagas) {
		this.vagas = vagas;
	}




}
