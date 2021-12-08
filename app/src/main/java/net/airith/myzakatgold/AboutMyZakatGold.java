package net.airith.myzakatgold;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutMyZakatGold extends AppCompatActivity {

    TextView linkTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_my_zakat_gold);

        linkTextView = findViewById(R.id.github_link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}