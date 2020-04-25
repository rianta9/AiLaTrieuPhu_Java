package Bean;

public class Cauhoi {
	private String cauhoi;
	private String luachon1;
	private String luachon2;
	private String luachon3;
	private String luachon4;
	private int dapan;
	private boolean used;
	
	
	
	public Cauhoi(String cauhoi, String luachon1, String luachon2, String luachon3, String luachon4, int dapan) {
		super();
		this.cauhoi = cauhoi;
		this.luachon1 = luachon1;
		this.luachon2 = luachon2;
		this.luachon3 = luachon3;
		this.luachon4 = luachon4;
		this.dapan = dapan;
		used = false;
	}

	

	public String getCauhoi() {
		used = true;
		return cauhoi;
	}

	public String getLuachon1() {
		return luachon1;
	}

	public String getLuachon2() {
		return luachon2;
	}

	public String getLuachon3() {
		return luachon3;
	}

	public String getLuachon4() {
		return luachon4;
	}

	public int getDapan() {
		return dapan;
	}


	public boolean isUsed() {
		if(used) return true;
		return false;
	}
	
	public boolean checkAnswer(int chon) {
		if(dapan == chon) return true;
		return false;
	}
}
