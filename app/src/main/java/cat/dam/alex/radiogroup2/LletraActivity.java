package cat.dam.alex.radiogroup2;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class LletraActivity extends AppCompatActivity {
    private RadioGroup rg_opcionsLletra;
    private RadioButton rb_lletraMoltPetita, rb_lletraPetita,
            rb_lletraMitjana, rb_lletraGran, rb_lletraMoltGran;
    private ObjectAnimator oa1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lletra);

        //components layout de lletra:
        rg_opcionsLletra= (RadioGroup) findViewById(R.id.rg_opcionsLletra);
        rb_lletraMoltPetita = (RadioButton) findViewById(R.id.rb_moltPetita);
        rb_lletraPetita = (RadioButton) findViewById(R.id.rb_petita);
        rb_lletraMitjana = (RadioButton) findViewById(R.id.rb_mitjana);
        rb_lletraGran = (RadioButton) findViewById(R.id.rb_gran);
        rb_lletraMoltGran = (RadioButton) findViewById(R.id.rb_moltGran);

                rg_opcionsLletra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // troba quin radio button s'ha seleccionat
                if (checkedId == R.id.rb_moltPetita) {
                    rg_opcionsLletra.setScaleX(0.8f);
                    rg_opcionsLletra.setScaleY(0.8f);
                } else if (checkedId == R.id.rb_petita){
                    rg_opcionsLletra.setScaleX(0.9f);
                    rg_opcionsLletra.setScaleY(0.9f);
                } else if (checkedId == R.id.rb_mitjana){
                    rg_opcionsLletra.setScaleX(1.0f);
                    rg_opcionsLletra.setScaleY(1.0f);

                } else if (checkedId == R.id.rb_gran){
                    rg_opcionsLletra.setScaleX(1.2f);
                    rg_opcionsLletra.setScaleY(1.2f);

                } else if (checkedId == R.id.rb_moltGran){
                    rg_opcionsLletra.setScaleX(1.4f);
                    rg_opcionsLletra.setScaleY(1.4f);
                }
            }
        });

    }
}
