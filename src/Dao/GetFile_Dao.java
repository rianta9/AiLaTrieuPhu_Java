package Dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Bean.Cauhoi;

public class GetFile_Dao {
	ArrayList<Cauhoi> list;
	
	public GetFile_Dao() {
		list = new ArrayList<Cauhoi>();
	}
	
	public ArrayList<Cauhoi> getFile(String filename) {
		try {
			File p = new File(filename);
			BufferedReader file = new BufferedReader( new InputStreamReader(new FileInputStream(p), "UTF8"));
			String cauhoi;
			String chon1;
			String chon2;
			String chon3;
			String chon4;
			String dapan;
			while(true) {
				cauhoi = file.readLine();
				if(cauhoi == null || cauhoi.isEmpty()) break;
				chon1 = file.readLine();
				if(chon1 == null || chon1.isEmpty()) break;
				chon2 = file.readLine();
				if(chon2 == null || chon2.isEmpty()) break;
				chon3 = file.readLine();
				if(chon3 == null || chon3.isEmpty()) break;
				chon4 = file.readLine();
				if(chon4 == null || chon4.isEmpty()) break;
				dapan = file.readLine();
				if(dapan == null || dapan.isEmpty()) break;
				list.add(new Cauhoi(cauhoi, chon1, chon2, chon3, chon4, Integer.parseInt(dapan)));
			}
			file.close();
			return list;
		} catch (IOException e) {
			return null;
		}
	}
	
}
