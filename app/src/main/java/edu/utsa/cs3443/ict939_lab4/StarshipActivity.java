package edu.utsa.cs3443.ict939_lab4;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class StarshipActivity extends AppCompatActivity {
    private Starship ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_starship);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ship = getIntent().getParcelableExtra(MainActivity.decodeIntent());

        assert ship != null;
        dynamicAddFleet(ship);


    }

    private void dynamicAddFleet(Starship ship) {


        TextView shipInfo = findViewById(R.id.textView);
        String shipInfoText = ship.getName() + " \n" + ship.getRegistry();
        shipInfo.setText(shipInfoText);
        shipInfo.setTextSize(23);
        shipInfo.setLetterSpacing(0.1f);
        AssetManager assets = this.getAssets();
        Typeface tf = Typeface.createFromAsset(assets,"Rubik-Black.ttf");
        shipInfo.setTypeface(tf, Typeface.BOLD);

        System.out.println("SHIP RETRIEVED");
        // create layout object
        LinearLayout rootLayout2 = (LinearLayout) findViewById(R.id.rootLayout2);
        ArrayList<CrewMember> fleetInfo = ship.getCrewMemberArray();

        for (CrewMember member : fleetInfo) {
            LinearLayout infoLayout = new LinearLayout(this);

            String imageFile = member.getFile();
            System.out.println(imageFile);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(this.getResources().getIdentifier(imageFile, "drawable", this.getPackageName()));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 35, 30, 0);

            layoutParams.width = 300;
            layoutParams.height = 300;

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setLayoutParams(layoutParams);

            LinearLayout verticalLayout = new LinearLayout(this);
            verticalLayout.setOrientation(LinearLayout.VERTICAL);
            verticalLayout.setGravity(Gravity.CENTER_VERTICAL);


            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams2.setMargins(0, 50, 0, 0);

            TextView textView = new TextView(this);
            textView.setText(member.getPosition());
            textView.setTextSize(25);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(getResources().getColor(R.color.white));

            TextView textView2 = new TextView(this);
            String textV2Info = member.getRank() + " " + member.getName();
            textView2.setText(textV2Info);
            textView2.setTextSize(15);
            textView2.setTextColor(getResources().getColor(R.color.white));

            verticalLayout.addView(textView, layoutParams2);
            verticalLayout.addView(textView2);

            infoLayout.addView(imageView, layoutParams);
            infoLayout.addView(verticalLayout);


            LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rootLayout2.addView(infoLayout, rootParams);

        }

    }


}