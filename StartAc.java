package ya.translate;

import com.example.yandex_translate.R;

import a.id.idealview.RaitFrameList;
import a.id.idealview.stors1;
import android.app.Activity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class StartAc extends Activity{
static	toDoDBOpenHelper OpenHelper;
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ChldView.chldView.getBaseOut();
	}
	@Override
	protected void onStop() {
		super.onStop();
		OpenHelper.myDatabase.close();
	}
	static StartAc startAc;		View[] v;
	RaitFrameList root;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startAc=this;
		OpenHelper =new toDoDBOpenHelper(this);
		setContentView(R.layout.root_root);
		 root=(a.id.idealview.RaitFrameList) findViewById(R.id.root_root);
	 addFrames(3);
		
		 addFrames(2);
		 addFrames(1);
		
		 v=new View[root.getChildCount()];
		 for(int i=0;i<root.getChildCount();i++){
			v[i]=	root.getChildAt(i);
		 }	
	}
@Override
	protected void onRestart() {
		super.onRestart();
		OpenHelper =new toDoDBOpenHelper(this);
	}
void addFrames(int Title){
	ChldView child=new ChldView(this,Title);
root.addView(child);
}
void reloadHistory(){
	((ChldView) v[1]).update();
}
void gotoIndex(int id){

	while(v[id].hashCode()!=root.getChildAt(root.getChildCount()-1).hashCode())
	root.replaceChildViewPlus();

}
private int getint(int id) {
	int out=id;
	if(out>2)out=Math.abs(out-3);
	return out;
}

}