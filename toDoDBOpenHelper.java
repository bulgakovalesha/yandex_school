package ya.translate;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

public class toDoDBOpenHelper {
	static String[] result_columns = new String[] {"_id","column_in", "column_tr","column_lang text"};
	private static final String DATABASE_NAME = "yandexTRDatabase.db";
	private static final String DATABASE_LAKE = "lake";
	private static final String DATABASE_HISTORY = "history";
	private static final String DATABASE_CREATE_T1 =
			"create table " + DATABASE_LAKE + " ( _id integer primary key autoincrement," +
						"column_in text,column_tr text,column_lang text);";
	private static final String DATABASE_CREATE_T2 =
			"create table " + DATABASE_HISTORY + " ( _id integer primary key autoincrement," +
						"column_in text,column_tr text,column_lang text);";
	static SQLiteDatabase myDatabase;
	public toDoDBOpenHelper(Context context) {
		super();
		myDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		Log.e("tagsdd", String.valueOf( myDatabase.isDatabaseIntegrityOk() ));
	}
	
	@SuppressWarnings("unchecked")
	static
	String[][] getReadText(String DATABASE_TABLE){
		@SuppressWarnings("rawtypes")
		ArrayList read=new ArrayList<String>();
			
		Cursor allRows ;
		
		try{	allRows 	= myDatabase.query(true, DATABASE_TABLE, result_columns,
				null, null, null, null, null, null);
		if(allRows.moveToFirst())do{
			read.add(allRows.getString(1));
			read.add(allRows.getString(2));	
			read.add(allRows.getString(3));	

		}while(allRows.moveToNext());
	}catch(android.database.sqlite.SQLiteException e){
		try{
			myDatabase.execSQL(DATABASE_CREATE_T1); 
			myDatabase.execSQL(DATABASE_CREATE_T2);
		}catch(android.database.sqlite.SQLiteException ee){}
		}
		
		String[][]	out =new String[read.size()/3][3];
		for(int i=0;i<out.length;i++){
			out[i][0]= (String) read.get(i*3);
			out[i][1]=(String) read.get(i*3+1);
			out[i][2]=(String) read.get(i*3+2);
		}
		return out;
	}
	void insertAndSend(String string1,String string2,String string3,int table) {
	
		
		String DATABASE_TABLE; if(table==1)DATABASE_TABLE=DATABASE_LAKE; 
		else DATABASE_TABLE=DATABASE_HISTORY; 
	
		ContentValues newValues = new ContentValues();
		newValues.put("column_in", string1);
		newValues.put("column_tr", string2);
		newValues.put("column_lang", string3);
myDatabase.insert(DATABASE_TABLE, null, newValues);


	}
	static class allArray{
	
		public static String[][] getArrayTop(){
			return getReadText(DATABASE_LAKE);
		}
		public static String[][] getArrayHistory(){
			return getReadText(DATABASE_HISTORY);
		}
	}
	public void removeRows(ArrayList frame2trash) {
		for(int i=0;i<frame2trash.size();i++){
		Cursor allRows = myDatabase.query(true, DATABASE_LAKE, result_columns,
				null, null, null, null, null, null);
		if(allRows.moveToFirst())do{
			String[] remowerow = (String[]) frame2trash.get(i);
	if(allRows.getString(1).equals(remowerow[0]) && allRows.getString(2).equals(remowerow[1]))
			myDatabase.delete(DATABASE_LAKE, "_id=" + allRows.getString(0), null);


		}while(allRows.moveToNext());	
		
	}
		for(int i=0;i<frame2trash.size();i++){
		Cursor allRows = myDatabase.query(true, DATABASE_HISTORY, result_columns,
				null, null, null, null, null, null);
		if(allRows.moveToFirst())do{
			String[] remowerow = (String[]) frame2trash.get(i);	
	if(allRows.getString(1).equals(remowerow[0]) && allRows.getString(2).equals(remowerow[1]))
			myDatabase.delete(DATABASE_HISTORY, "_id=" + allRows.getString(0), null);

		}while(allRows.moveToNext());
	}
	}
}