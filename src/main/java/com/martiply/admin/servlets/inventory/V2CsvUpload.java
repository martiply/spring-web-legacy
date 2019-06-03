package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

import com.martiply.dao.StandardDAO;
import com.martiply.model.ApparelExtension;
import com.martiply.model.BasicItem;
import com.martiply.utils.Rules;

public class V2CsvUpload {

	CSVReader reader;
	private String eLTemplate = "error reading file at row %d : %s";
	public String error;
	
	public ArrayList<BasicItem> items = new ArrayList<BasicItem>();
	public ArrayList<ApparelExtension> apparels = new ArrayList<>();
	
	
	public V2CsvUpload(CSVReader csvReader) {
		this.reader = csvReader;
	}

	public void read(int ownerId) {
		int i = 0;
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				i++;
				
				if (i > Rules.MAX_UPLOAD_ITEMS){
					error = String.format("Maximum %d items per upload", Rules.MAX_UPLOAD_ITEMS);
					return;
				}

				switch (i) {
				case 1:
					if (nextLine.length != 13 && nextLine.length != 21) {
						error = "Wrong table structure";
						return;
					}
					break;

				default:
					SqlUpload sqlUpload = new SqlUpload(ownerId);

					switch (nextLine.length) {

					case SqlUpload.LENGTH_COMPLETE:
						String er = sqlUpload.testApparel(nextLine[13], nextLine[14], nextLine[15], nextLine[16], nextLine[17], nextLine[18], nextLine[19], nextLine[20]);
						if (er != null) {
							error = String.format(eLTemplate, i, er);
							return;
						}
						
					case SqlUpload.LENGTH_DEFAULT:
						er = sqlUpload.testBasic(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9]);
						if (er != null) {
							error = String.format(eLTemplate, i, er);
							return;
						}
						er = sqlUpload.testSaleCsv(nextLine[10], nextLine[11], nextLine[12]);
						if (er != null) {
							error = String.format(eLTemplate, i, er);
							return;
						}

					default: 
						BasicItem item = sqlUpload.item;
						item.setId(StandardDAO.makeKey(item));
						items.add(item);
						
						if (sqlUpload.apparelExtension != null){
							sqlUpload.apparelExtension.setId(item.getId());
							apparels.add(sqlUpload.apparelExtension);
						}
					}

					break;
				}

			}
		} catch (IOException e) {
			error = "error reading file";
		}
	}

	

}
