package ya.translate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;


public class Post extends AsyncTask<Object, Object, Object>{
	URL url;HttpsURLConnection connect;InputStream inputStream;
	onDataIn ondataIn;
	private String data;
	
	public static String Key="key=trnsl.1.1.20170401T122518Z.3b606699b7d4c2bb.be6905e4085ad436d4152fdcb51907244caa8025";
public	String BASE ="https://translate.yandex.net/api/v1.5/tr.json/translate?&options=1&"+Key;
public	String BASE1 ="https://translate.yandex.net/api/v1.5/tr.json/getLangs?"+Key;
public	String BASE2 =" https://dictionary.yandex.net/api/v1/dicservice.json/lookup?"+Key;
public void setonDataIn(onDataIn ondataIn){
this.ondataIn=ondataIn;
if(ondataIn!=null)ondataIn.setOnGetAccaunts(data);
}



	@Override
	protected Object doInBackground(Object... arg0) {
		String out = "";

	try {
		url = new URL((String) arg0[0]);
	
		connect = (HttpsURLConnection) url.openConnection();
		
		connect.setDoInput(true);
		connect.setRequestMethod("POST");
	
OutputStreamWriter writer = new OutputStreamWriter(connect.getOutputStream());
writer.write((String)arg0[0]);
writer.flush();

	
	} catch (IOException e) {
		Log.e("tag", String.valueOf(e.getMessage()));
		
	}
	
	 try {
		out= readMultipleLinesRespone();
	} catch (IOException e) {
		e.printStackTrace();
		Log.e("tag", String.valueOf(e.getMessage()));
	}
	 final String output = out;   
		 		 data=output;setonDataIn(ondataIn);
	return  out;
	}
	public String readMultipleLinesRespone() throws IOException {
		InputStream inputStream;
		if (connect != null) {
	inputStream = connect.getInputStream();
		} else {
			throw new IOException("!");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
	String response ="";
		String line;
		while ((line = reader.readLine()) != null) {
			response+=line;
		}
		reader.close();
		
		return response;
	}
	public interface onDataIn {
		public void setOnGetAccaunts(String data);
	}
}
