package ya.translate;



import java.util.ArrayList;

import ya.translate.Post.onDataIn;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.yandex_translate.R;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;


import android.view.View;



import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView.OnEditorActionListener;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ChldView extends RelativeLayout{
	static ChldView chldView;
	int frame2up=0;
	// массив строк неизвесной длинны для удаления
	ArrayList<String[]> frame2trash;
	// результаты после запросов языков
	JSONArray data1;public static  JSONObject data2;
	public static  String[][] langs;
	
	TextView title;
	//  3 нижних кнопки
	ImageView i1,i2,i3;

	
	// поле ввода
   EditText edittext;
   // основной слой
	LinearLayout rtrtr;
	int position; 
	//
	public static String check="en",sucheck="en-ru";
	//
TableLayout tl;
// поле вывода
TextView outText;int H;
	private void init(Context context,int  num) {
		chldView=this;
		 DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
	      
	        H = dm.heightPixels;
	ScrollView syu=new ScrollView(getContext());
		 rtrtr=new LinearLayout(getContext());
		 RelativeLayout.LayoutParams paramsq=new RelativeLayout.LayoutParams(-1,-1);
		 paramsq.setMargins(15, 0, 15, 100);
		
		rtrtr.setOrientation(LinearLayout.VERTICAL);
		
	syu.setPadding(15 ,0, 15, 0);
	syu.addView(rtrtr);
	

		addView(syu);

	syu.setLayoutParams(paramsq);
	syu.setY(120);
		setBackgroundColor(0xffffffff);
		// параметры титла
	title=new TextView(context);
	title.setTextSize(20);
	title.setTextColor(0xff000000);
	title.setGravity(Gravity.CENTER);
	title.setBackgroundColor(0xffffdc61);
    title.setPadding(0, 20, 0, 20);
if(num!=1)
addView(title,-1,-2);
         tl=new TableLayout(context);
         tl.setBackgroundColor(0xffffffff);
     
         // параметры нижней таблицы, куда входят 3 кнопки   
         
        TableLayout.LayoutParams prochparams=new TableLayout.LayoutParams(-1,2);
        prochparams.setMargins(0, 20, 0, 20);
    
        TableRow tr=new TableRow(context);
        tl.addView(tr,-1,-2);
        tl.setPadding(10, 10, 10, 10);
        tl.setStretchAllColumns(true);
        RelativeLayout.LayoutParams params=new LayoutParams(-1,-2);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    		params.setMargins(0, 20, 0, 0);
    		tl.setLayoutParams(params);
		
    i1=new ImageView(context);  
   i1.setImageResource(R.drawable.tr_noactiv);
  
   tr.addView(i1);
  i2=new ImageView(context);
   tr.addView(i2);
   //  обработка кнопок 
   i1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		StartAc.startAc.gotoIndex(2);
			
		
			
		}
	});
   i2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		StartAc.startAc.gotoIndex(1);	
		}
	});
   i2.setImageResource(R.drawable.save_noactive);
   i3=new ImageView(context);
   tr.addView(i3);
   i3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			StartAc.startAc.gotoIndex(0);
		}
	});
   i3.setImageResource(R.drawable.setting_noactive);
        
        addView(tl);
        // уходим в разные парамеры отображения в зависимости от номера 
        switch (num){
		case 1:
			void1();
			break;
		case 2:
			void2();
			break;
		case 3:
			void3();
			break;
		}

	}

	

	void void1(){
		title.setText("Перевод");
		title.setId(85455);
		 i1.setImageResource(R.drawable.tr);
	 RelativeLayout TextEndButtons=new RelativeLayout(getContext());
	TextEndButtons.setPadding(20, 20, 20, 0);
		 
			 edittext=new EditText(getContext());
			 //  TextEndButtons совмещает окно ввода икнопки
			 TextEndButtons.addView(edittext,-1, H /4);
		
		outText=new TextView(getContext());
		outText.setPadding(20, 20, 20, 0);
	LinearLayout lineshoriz=new LinearLayout(getContext());
	lineshoriz.addView(title,-1,-2);
		lineshoriz.setOrientation(LinearLayout.VERTICAL);
		
		lineshoriz.addView(TextEndButtons,-1, H /4);
		addView(lineshoriz,-1,-1);
		RelativeLayout.LayoutParams ppp=new LayoutParams(-2,-2);
		ppp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ppp.setMargins(10,  H /6, 10, 0);
		lineshoriz.addView(outText,-1,-2);
		ImageView imadd=new ImageView(getContext());
		imadd.setImageResource(R.drawable.saved); TextEndButtons.addView(imadd);
		
		imadd.setLayoutParams(ppp);
		// обработка кнопки избранное
		imadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(edittext.getText().length()!=0)
				StartAc.OpenHelper.insertAndSend(edittext.getText().toString(), outText.getText().toString(), sucheck, 1);
				StartAc.startAc.reloadHistory();
			}
		});
		ImageView imad=new ImageView(getContext());
	    imad.setPadding(10, H /6, 0, 0);
		imad.setImageResource(R.drawable.ic_clear_search_api_holo_light);
		TextEndButtons.addView(imad);
		//  обработка кнопки очистить
		imad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(edittext.getText().length()!=0)
				StartAc.OpenHelper.insertAndSend(edittext.getText().toString(), outText.getText().toString(), sucheck, 2);
				edittext.setText("");outText.setText("");
				StartAc.startAc.reloadHistory();
			}
		});
		
		
	
		// размер и цвет поля вывода
			outText.setTextColor(0xff000000);outText.setTextSize(24);
			// отслеживание поля ввода
		TextWatcher generateTextWatcher=new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					outText.setText("");
					//  посылка запроса после ввода при каждой новой букве	
					if(s.length()!=0)
					translate("&lang="+check+"&text="+s.toString().replace(" ", "%20"));
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				
				@Override
				public void afterTextChanged(Editable s) {
				}
			};
			edittext.addTextChangedListener(generateTextWatcher);
			
	}
void void2(){
	title.setText("История");
	// обработка титла 2 окна
	title.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(frame2up==0){frame2up=1;
			title.setText("Избранное");
			String[][] arr1 = toDoDBOpenHelper.allArray.getArrayTop();
			if(arr1.length >0){
				frame2trash =new ArrayList<String[]>();
			
			HistryUpdate(arr1, new String[0][0]);
			}
			}else{ frame2up=0;
			title.setText("История");
			update();
			}
			
		}
	});
	RelativeLayout  trr=new RelativeLayout(getContext());
	addView(trr,-1,-2);
	trr.setGravity(Gravity.CENTER_VERTICAL);
	// кнопка удалить
	ImageView trash=new ImageView(getContext());
	trash.setId(354546);
	RelativeLayout.LayoutParams p=new RelativeLayout.LayoutParams(-2,-2);
	p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	trash.setLayoutParams(p);
	trash.setImageResource(R.drawable.trash);
	// кнопка снять выделение
	ImageView btn_close_normal=new ImageView(getContext());
	btn_close_normal.setId(354549);
btn_close_normal.setImageResource(R.drawable.btn_close_normal);
btn_close_normal.setPadding(20, 20, 0, 0);
	trr.addView(btn_close_normal);
	trash.setVisibility(GONE);
	btn_close_normal.setVisibility(GONE);
	// приводим все в начальное положение
	btn_close_normal.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			update();
			findViewById(354546).setVisibility(GONE);
			v.setVisibility(GONE);
		}
	});
	// удаляем все, что есть в frame2trash
	trash.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			StartAc.OpenHelper.removeRows(frame2trash);
			findViewById(354549).setVisibility(GONE);
			v.setVisibility(GONE);
			frame2up=0;
			title.setText("История");
			update();
		}
	});
	trr.addView(trash);
	 i2.setImageResource(R.drawable.save);
	setBackgroundColor(0xffeeeeee);
update();

	}
void update() {
	// у нас два массива - "избранное" и "история"; получаем их из базы
	String[][] arr2 = toDoDBOpenHelper.allArray.getArrayHistory();
	String[][] arr1 = toDoDBOpenHelper.allArray.getArrayTop();
	
	// создаем новый список для удаления
	frame2trash =new ArrayList<String[]>();

	HistryUpdate(arr1, arr2);	
}
private void HistryUpdate(final String[][] arr1,final String[][] arr2) {
	if(getHandler()!=null)
	getHandler().post(new Runnable() {		
		@Override
		public void run() {
			rtrtr.removeAllViews();
			HistryUpdatethis(arr1,R.drawable.saved);
			HistryUpdatethis(arr2,R.drawable.nosaved);
		}
	});else {
		rtrtr.removeAllViews();
		HistryUpdatethis(arr1,R.drawable.saved);
		HistryUpdatethis(arr2,R.drawable.nosaved);
	}
}
private void HistryUpdatethis(String[][] arr,int icon){
	
	for(int i=0;i<arr.length;i++){

		// слой отображения истории или избранного	
	final LinearLayout r = new LinearLayout(getContext());

	r.setPadding(0, 10, 10, 10);
	r.setOrientation(LinearLayout.HORIZONTAL);
	r.setGravity(Gravity.CENTER_VERTICAL);
	 ImageView im=new ImageView(getContext());
	 r.addView(im);
	 TextView langs=new TextView(getContext());
	 langs.setText(arr[i][2]);
	 langs.setGravity(Gravity.RIGHT);
	 
	 im.setPadding(0, 0, 20, 0);
	 LinearLayout run = new LinearLayout(getContext());
	 run.setOrientation(LinearLayout.VERTICAL);
	 // запрос
	 final TextView en=new TextView(getContext());
	 // перевод
	 final TextView ru=new TextView(getContext());
	 en.setTextColor(0xff000000);
	 ru.setTextColor(0xff888888);
	 en.setText(arr[i][0]);
	 ru.setText(arr[i][1]);
	 run.addView(en);
	 run.addView(ru);
	 
	 r.addView(run);
	 r.addView(langs,-1,-1);

	 im.setImageResource(icon); 
	 im.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				r.setBackgroundColor(0x55aa6677);
				findViewById(354546).setVisibility(VISIBLE);
				findViewById(354549).setVisibility(VISIBLE);
				String[] remowerow1=new String[]{en.getText().toString(),ru.getText().toString()};
				frame2trash.add(remowerow1);
			}
		});
	 
	 rtrtr.addView(r);
	 
	}
	
}

void void3(){
	title.setText("Настройки");
	setBackgroundColor(0xffdddddd);

	
	 i3.setImageResource(R.drawable.setting);
	Post p=new Post();
	p.execute(p.BASE1+"&ui=ru");
p.setonDataIn(new onDataIn() {
		
		@Override
		public void setOnGetAccaunts(String data) {
			JSONObject dataJsonObj;
			if(data!=null)
				try {
					// разбираем, что пришло
					dataJsonObj = new JSONObject(data);
					 data1 = dataJsonObj.getJSONArray("dirs");
					data2 = dataJsonObj.getJSONObject("langs");
					 langs=new String[data2.length()][2];
					for(int i=0;i<data2.length();i++)
				{       // заполняем массив с языками
						langs[i][0]=String.valueOf(data2.names().get(i));
						langs[i][1]=data2.getString(langs[i][0]);
		}
					SettinUpdate(); //data2.toString(0)
				} catch (JSONException e) {
					Log.e("JSONException", String.valueOf(e.getMessage()));
				}
		}
	});
}
	void SettinUpdate() {
		
		getHandler().post(new Runnable() {
			
			@Override
			public void run() {

			for(int i=0;i<langs.length;i++){
				// слой для заполнения языками
				final LinearLayout r = new LinearLayout(getContext());
				r.setPadding(100, 10, 10, 10);
				r.setOrientation(LinearLayout.HORIZONTAL);
				r.setGravity(Gravity.CENTER_VERTICAL);
			
			ImageView im=new ImageView(getContext());
			im.setId(i+1000);
			im.setPadding(10, 0, 20, 0);
			if(langs[i][0].equals(check)){
				position=i+1000;
			im.setImageResource(R.drawable.btn_check_buttonless_on);
			}
			else im.setImageResource(R.drawable.btn_check_off_disable);
			r.addView(im);
			//  ставим галочку, меняя направление перевода
			im.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					((ImageView) findViewById(position)).setImageResource(R.drawable.btn_check_off_disable);
				check = langs[v.getId()-1000][0];
					
				
				position=v.getId();
				((ImageView) v).setImageResource(R.drawable.btn_check_buttonless_on);
				}
			});
			//  непосредственно язык
				TextView t=new TextView(getContext());
				t.setGravity(Gravity.CENTER);
				t.setTextColor(0xff000000);
				String s="";
				
					s = langs[i][1];
					t.setText(s);
					r.addView(t);
					rtrtr.addView(r);
			
		        
				}
			rtrtr.setVisibility(View.VISIBLE);;
			}
		});
		
	}
	// класс для активности 
public ChldView(Context context,int Title) {
		super(context);
		init(context,Title);
	}

// запрос и заполнение поля ввывода и титла для первого окна
void translate(String in){
	Post p=new Post();
p.execute(p.BASE+in);
	p.setonDataIn(new onDataIn() {
		
		@Override
		public void setOnGetAccaunts(String data) {
			if(data!=null)
				try {
					JSONObject	dataq = new JSONObject(data);
					if(dataq.getInt("code")==200){
				final JSONArray
						 text = dataq.getJSONArray("text");
				
				sucheck = dataq.getString("lang");
				 	
					getHandler().post(new Runnable() {
						
						@Override
						public void run() {
							try {
								for(int i=0;i<	text.length();i++)
							outText.setText(text.getString(i)+"\n");
								String[] newlang = sucheck.split("-");
								if(data2!=null){
							String ooo = (String) data2.get(		newlang[0]);
							if(newlang.length>1) ooo+= "   <-->  "+(String) data2.get(		newlang[1]);
								title.setText(ooo);
								}else title.setText(sucheck);
							
							} catch (JSONException e) {
								outText.setText("");
								e.printStackTrace();
							}
						}
					});
					
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}	
		}
	});
}
}