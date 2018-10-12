package android.curseudemy.com.curso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textViewCatcher);

        //tomsr los datos del intent
        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.getString("greeter") != null){
            String greeter = bundle.getString("greeter");
            Toast.makeText(this, greeter, Toast.LENGTH_LONG).show();
            textView.setText(greeter);
        } else {
            Toast.makeText(this, "Nada para mostrar...", Toast.LENGTH_LONG).show();
        }

        //Boton para ir al tercer activity
        more = (Button) findViewById(R.id.buttonActions);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
}
