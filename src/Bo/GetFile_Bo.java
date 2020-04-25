package Bo;

import java.util.ArrayList;

import Bean.Cauhoi;
import Dao.GetFile_Dao;

public class GetFile_Bo {
	ArrayList<Cauhoi> list;
	GetFile_Dao a;
	
	public GetFile_Bo() {
		a = new GetFile_Dao();
	}
	
	public ArrayList<Cauhoi> getFile(String filename) {
		list = a.getFile(filename);
		return list;
	}
}
