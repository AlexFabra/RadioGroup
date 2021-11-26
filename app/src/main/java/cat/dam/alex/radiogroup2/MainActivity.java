package cat.dam.alex.radiogroup2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_color,ll_lletra;
    private Button btn_aplicarColor, btn_aplicarLletra;
    private EditText et_cll,et_cfll,et_cf;

    private RadioGroup rg_opcions;
    private RadioButton rb_lletra, rb_color;
    private TextView tv_configurable;
    private LinearLayout ll_variable;

    private RadioGroup rg_opcionsLletra;
    private RadioButton rb_lletraMoltPetita, rb_lletraPetita,
            rb_lletraMitjana, rb_lletraGran, rb_lletraMoltGran;

    private RadioGroup rg_opcionsColorLletra, rg_opcionsColorFLletra, rg_opcionsColorF;

    private RadioButton rb_ocl_vermell, rb_ocl_blau, rb_ocl_negre, rb_ocl_blanc, rb_ocl_verd,
            rb_ocfl_vermell, rb_ocfl_verd, rb_ofcl_blau, rb_ofcl_blanc, rb_ofcl_negre,
            rb_ocf_vermell,rb_ocf_verd,rb_ocf_blau,rb_ocf_blanc,rb_ocf_negre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //components layout principal:
        rg_opcions = (RadioGroup) findViewById(R.id.rg_opcions);
        rb_lletra = (RadioButton) findViewById(R.id.rb_lletra);
        rb_color = (RadioButton) findViewById(R.id.rb_color);
        tv_configurable = (TextView) findViewById(R.id.tv_configurable);
        ll_variable = (LinearLayout) findViewById(R.id.ll_Variable);

        rg_opcions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // si el radio seleccionat es rb.lletra:
                if (checkedId == R.id.rb_lletra) {
                    //borrem el view guardat en el linearLayout ll_variable
                    ll_variable.removeAllViews();
                    //i posem el activity com a nou valor de ll_variable
                    View child = getLayoutInflater().inflate(R.layout.activity_lletra, null);
                    ll_variable.addView(child);
                    //actualitzem les variables que utilitzarem:
                    identificaVariablesLayoutLletres();

                    //escoltem el radioButton que es toca del radioGroup rg_opcionsLletra:
                    rg_opcionsLletra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            //el id determina el botó que s'ha tocat:
                            changeSizeButton(group, checkedId);
                        }
                    });
                //si el radio button seleccionat es rb.color...:
                } else {

                    //borrem el view guardado en el linearLayout ll_variable:
                    ll_variable.removeAllViews();
                    //afegim el activity de modificació dels colors:
                    View child = getLayoutInflater().inflate(R.layout.activity_color,null);
                    ll_variable.addView(child);
                    //actualitzem les variables que utilitzarem al layout abans d'utilitzar-les:
                    identificaVariablesLayoutColors();

                    //els següents listeners executen una funció different cadascú
                    // a la que li passen el RadioGroup i l'id per canviar la lletra, el color del fons de la lletra
                    //o el color del fons, segons el radioGroup que canvii.
                    rg_opcionsColorLletra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            //el id determina el botó que s'ha tocat:
                            changeLetterColorButton(group, checkedId);
                        }
                    });
                    rg_opcionsColorFLletra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            //el id determina el botó que s'ha tocat:
                            changeUnderliningColorButton(group, checkedId);
                        }
                    });
                    rg_opcionsColorF.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            //el id determina el botó que s'ha tocat:
                            changeColorButton(group, checkedId);
                        }
                    });

                    //cuando se clique el btn_aplicarColor...
                    btn_aplicarColor.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            //comprobamos que los edit text tengan información antes de proceder a trabajar con sus datos:
                            if (!TextUtils.isEmpty(et_cll.getText().toString())) {
                                //guardamos el valor del EditText en un String:
                                String colorHexadecimal = et_cll.getText().toString();
                                //si coincide con l valor hexadecimal de un color:
                                if(esHexadecimal(colorHexadecimal)){
                                    //le añadimos la almohadilla:
                                    colorHexadecimal = '#'+ colorHexadecimal;
                                    //llamamos a la función que transforma el String a objeto color y lo aplicamos en nuestro tv con setTextColor.
                                    tv_configurable.setTextColor(Color.parseColor(colorHexadecimal));
                                }
                            }
                            if (!TextUtils.isEmpty(et_cfll.getText().toString())) {
                                //guardamos el dato del EditText en un String:
                                String colorHexadecimal = et_cfll.getText().toString();
                                if(esHexadecimal(colorHexadecimal)){
                                    //le añadimos la almohadilla:
                                    colorHexadecimal = '#'+ colorHexadecimal;
                                    tv_configurable.setBackgroundColor(Color.parseColor(colorHexadecimal));
                                }
                            }
                            if (!TextUtils.isEmpty(et_cf.getText().toString())) {
                                //guardamos el dato del EditText en un String:
                                String colorHexadecimal = et_cf.getText().toString();
                                if(esHexadecimal(colorHexadecimal)){
                                    //le añadimos la almohadilla:
                                    colorHexadecimal = '#'+ colorHexadecimal;
                                    ll_color.setBackgroundColor(Color.parseColor(colorHexadecimal));
                                }
                            }
                            mostrarMissatge();
                        }
                    });
                }
            }
        });
    }

    /** changeColorButton edita el color del TextView segons el botó
     *  que s'ha clicat del RadioGroup
     * @param group RadioGroup
     * @param checkedId id del botó clicat
     */
    public void changeLetterColorButton( RadioGroup group, int checkedId){
        // troba quin radio button s'ha seleccionat
        if (checkedId == R.id.rb_ocl_vermell) {
            canviarColorTv(tv_configurable,"colorRed");
        } else if(checkedId == R.id.rb_ocl_verd) {
            canviarColorTv(tv_configurable,"colorGreen");
        } else if(checkedId == R.id.rb_ocl_blau) {
            canviarColorTv(tv_configurable,"colorBlue");
        } else if(checkedId == R.id.rb_ocl_blanc) {
            canviarColorTv(tv_configurable,"colorWhite");
        } else if(checkedId == R.id.rb_ocl_negre) {
            canviarColorTv(tv_configurable,"colorBlack");
        }
    }

    /** changeColorButton edita el color del fons del TextView segons el botó
     *  que s'ha clicat del RadioGroup
     * @param group RadioGroup
     * @param checkedId id del botó clicat
     */
    public void changeUnderliningColorButton( RadioGroup group, int checkedId){
        // troba quin radio button s'ha seleccionat
        if (checkedId == R.id.rb_ocfl_vermell) {
            canviarColorFTv(tv_configurable,"colorRed");
        } else if(checkedId == R.id.rb_ocfl_verd) {
            canviarColorFTv(tv_configurable,"colorGreen");
        } else if(checkedId == R.id.rb_ocfl_blau) {
            canviarColorFTv(tv_configurable,"colorBlue");
        } else if(checkedId == R.id.rb_ocfl_blanc) {
            canviarColorFTv(tv_configurable,"colorWhite");
        } else if(checkedId == R.id.rb_ocfl_negre) {
            canviarColorFTv(tv_configurable,"colorBlack");
        }
    }

    /** changeColorButton edita el color del fons del LinearLayout segons el botó
     *  que s'ha clicat del RadioGroup
     * @param group RadioGroup
     * @param checkedId id del botó clicat
     */
    public void changeColorButton( RadioGroup group, int checkedId){
        // troba quin radio button s'ha seleccionat
        if (checkedId == R.id.rb_ocf_vermell) {
            canviarFonsLl(ll_color,"colorRed");
        } else if(checkedId == R.id.rb_ocf_verd) {
            canviarFonsLl(ll_color,"colorGreen");
        } else if(checkedId == R.id.rb_ocf_blau) {
            canviarFonsLl(ll_color,"colorBlue");
        } else if(checkedId == R.id.rb_ocf_blanc) {
            canviarFonsLl(ll_color,"colorWhite");
        } else if(checkedId == R.id.rb_ocf_negre) {
            canviarFonsLl(ll_color,"colorBlack");
        }
    }

    /** changeSizeButton edita la mida de l botó tv_configurable
     *  i la mida dels radioButtons segons el que esculli l'usuari.
     * @param group
     * @param checkedId
     */
    public void changeSizeButton(RadioGroup group, int checkedId){
        // troba quin radio button s'ha seleccionat
        if (checkedId == R.id.rb_moltPetita) {
            rg_opcionsLletra.setScaleX(0.8f);
            rg_opcionsLletra.setScaleY(0.8f);
            canviarMidaTv(tv_configurable,"mp");
        } else if (checkedId == R.id.rb_petita){
            rg_opcionsLletra.setScaleX(0.9f);
            rg_opcionsLletra.setScaleY(0.9f);
            rg_opcionsLletra.setScaleY(0.9f);
            canviarMidaTv(tv_configurable,"p");
        } else if (checkedId == R.id.rb_mitjana){
            rg_opcionsLletra.setScaleX(1.0f);
            rg_opcionsLletra.setScaleY(1.0f);
            canviarMidaTv(tv_configurable,"m");
        } else if (checkedId == R.id.rb_gran){
            rg_opcionsLletra.setScaleX(1.1f);
            rg_opcionsLletra.setScaleY(1.1f);
            canviarMidaTv(tv_configurable,"g");

        } else if (checkedId == R.id.rb_moltGran){
            rg_opcionsLletra.setScaleX(1.2f);
            rg_opcionsLletra.setScaleY(1.2f);
            canviarMidaTv(tv_configurable,"mg");
        }
    }

    public void identificaVariablesLayoutLletres(){

        ll_lletra = (LinearLayout) findViewById(R.id.ll_layoutLletra);

        rg_opcionsLletra= (RadioGroup) findViewById(R.id.rg_opcionsLletra);
        rb_lletraMoltPetita = (RadioButton) findViewById(R.id.rb_moltPetita);
        rb_lletraPetita = (RadioButton) findViewById(R.id.rb_petita);
        rb_lletraMitjana = (RadioButton) findViewById(R.id.rb_mitjana);
        rb_lletraGran = (RadioButton) findViewById(R.id.rb_gran);
        rb_lletraMoltGran = (RadioButton) findViewById(R.id.rb_moltGran);
    }

    public void identificaVariablesLayoutColors(){

        ll_color = (LinearLayout) findViewById(R.id.ll_layoutColor);
        btn_aplicarColor = (Button) findViewById(R.id.btn_aplicarColor);

        et_cll = (EditText) findViewById(R.id.et_cll);
        et_cfll = (EditText) findViewById(R.id.et_cfll);
        et_cf = (EditText) findViewById(R.id.et_cf);

        rg_opcionsColorLletra = (RadioGroup) findViewById(R.id.rg_opcionsColorLletra);
        rg_opcionsColorFLletra = (RadioGroup) findViewById(R.id.rg_opcionsColorFLletra);
        rg_opcionsColorF = (RadioGroup) findViewById(R.id.rg_opcionsColorF);

        rb_ocl_vermell = (RadioButton) findViewById(R.id.rb_ocl_vermell);
        rb_ocl_blau = (RadioButton) findViewById(R.id.rb_ocl_blau);
        rb_ocl_negre = (RadioButton) findViewById(R.id.rb_ocl_negre);
        rb_ocl_blanc = (RadioButton) findViewById(R.id.rb_ocl_blanc);
        rb_ocl_verd = (RadioButton) findViewById(R.id.rb_ocl_verd);
        rb_ocfl_vermell = (RadioButton) findViewById(R.id.rb_ocfl_vermell);
        rb_ocfl_verd = (RadioButton) findViewById(R.id.rb_ocfl_verd);
        rb_ofcl_blau = (RadioButton) findViewById(R.id.rb_ocfl_blau);
        rb_ofcl_blanc = (RadioButton) findViewById(R.id.rb_ocfl_blanc);
        rb_ofcl_negre = (RadioButton) findViewById(R.id.rb_ocfl_negre);
        rb_ocf_vermell = (RadioButton) findViewById(R.id.rb_ocf_vermell);
        rb_ocf_verd = (RadioButton) findViewById(R.id.rb_ocf_verd);
        rb_ocf_blau = (RadioButton) findViewById(R.id.rb_ocf_blau);
        rb_ocf_blanc = (RadioButton) findViewById(R.id.rb_ocf_blanc);
        rb_ocf_negre = (RadioButton) findViewById(R.id.rb_ocf_negre);
    }

    public static float convertSpToPixels(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /** canviarMidaTv canvia la mida del TextView passat com a primer paràmetre per
     *  la mida en String passada com a segón paràmetre
     * @param tvConf TextView
     * @param mida String
     */
    public void canviarMidaTv(TextView tvConf,String mida){
        //guardamos los resources en esta variable para poder acceder a ellos:
        Resources res = this.getResources();
        //encontramos el que nos interesa:
        int resId = res.getIdentifier(mida, "dimen", this.getPackageName());
        int textSizeInSp = (int) getResources().getDimension(resId);
        tvConf.setTextSize(convertSpToPixels(textSizeInSp, getApplicationContext()));
    }

    /** canviarColorFTv canvia el color del TextView passat com a primer parametre
     *  pel color passat com a segón paràmetre
     * @param tvConf TextView
     * @param color String
     */
    public void canviarColorTv( TextView tvConf,String color){
        //guardamos los resources en esta variable para poder acceder a ellos:
        Resources res = this.getResources();
        //encontramos el que nos interesa:
        int resId = res.getIdentifier(color, "color", this.getPackageName());
        int colorId = getResources().getColor(resId);
        tvConf.setTextColor(colorId);
    }

    /** canviarColorFTv canvia el color de fons del TextView passat com a primer parametre
     *  pel color passat com a segón paràmetre
     * @param tvConf TextView
     * @param color String
     */
    public void canviarColorFTv( TextView tvConf,String color){
        //guardamos los resources en esta variable para poder acceder a ellos:
        Resources res = this.getResources();
        //encontramos el que nos interesa:
        int resId = res.getIdentifier(color, "color", this.getPackageName());
        int colorId = getResources().getColor(resId);
        tvConf.setBackgroundColor(colorId);
    }

    /** canviarFonsLl canvia el color del linearLayout passat com a primer paràmetre
     *  per el color passat com a segón parametre
     * @param ll LinearLayout
     * @param color String
     */
    public void canviarFonsLl(LinearLayout ll, String color){
        //guardamos los resources en esta variable para poder acceder a ellos:
        Resources res = this.getResources();
        //encontramos el que nos interesa:
        int resId = res.getIdentifier(color, "color", this.getPackageName());
        int colorId = getResources().getColor(resId);
        ll.setBackgroundColor(colorId);
    }

    /** esHexadecimal comprova si un string té el pattern exadecimal (sense #)
     * @param colorHexadecimal
     * @return true si és hexadecimal
     */
    public static boolean esHexadecimal(String colorHexadecimal){
        //creamos un pattern para discernir códigos hexadecimales:
        Pattern pattern = Pattern.compile("([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
        Matcher matcher = pattern.matcher(colorHexadecimal);
        //si el colorHexadecimal encaja con el pattern...
        if (matcher.matches()){
            return true;
        } else {
            return false;
        }
    }

    /** mostrarMissatge recull informació dels views i les mostra en un toast
     *  informa de la mida, el color de lletra, el color de fons de un textview concret
     *  i també del color de fons de un layout concret
     *  mostra la informació dels colors en hexadecimal.
     */
    public void mostrarMissatge(){
        int colorLletra;
        int colorFonsLletra;
        int colorFons;
        String hexColorLletra;
        String hexColorFonsLletra;
        String hexColorFons;
        String missatge="Tamany de la lletra: ";

        //passem el colorFonsLletra a hexadecimal, ja que getCurrentTextColor no ens dona ni decimal ni hexadecimal:
        colorLletra=tv_configurable.getCurrentTextColor();
        hexColorLletra = String.format("#%06X", (0xFFFFFF & colorLletra));

        //per treure el color del background de la lletra:
        colorFonsLletra = Color.TRANSPARENT;
        Drawable background = tv_configurable.getBackground();
        if (background instanceof ColorDrawable)
            colorFonsLletra = ((ColorDrawable) background).getColor();
            hexColorFonsLletra = String.format("#%06X", (0xFFFFFF & colorFonsLletra));

        //per treure el color del background del fons:
        colorFons=((ColorDrawable)ll_color.getBackground()).getColor();
        hexColorFons=String.format("#%06X", (0xFFFFFF & colorFons));

        missatge+=tv_configurable.getTextSize();
        missatge+=" px. \ncolor de la lletra: ";
        missatge+=hexColorLletra;
        missatge+="\ncolor del fons de la lletra: ";
        missatge+=hexColorFonsLletra;
        missatge+="\ncolor del fons: ";
        missatge+=hexColorFons;

        Toast.makeText(MainActivity.this, missatge, Toast.LENGTH_SHORT).show();
    }



//  aquesta seria una altra possible estructuració per gestionar els botons:
//    public void aplicar(View v) {
//        final int id= v.getId();
//        switch(id){
//            case R.id.btn_aplicarColor:
//                System.out.println("ej");
//                break;
//            case R.id.btn_aplicarLletra:
//                System.out.println("je");
//                break;
//        }
//    }
}