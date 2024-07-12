package edu.utsa.cs3443.ict939_lab4;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private final Fleet fleet = new Fleet("LAB 4 Fleet");
    private static final String intentKey = "starship_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadData();
        dynamicAddShips(fleet);


    }

    private void loadData() {
        fleet.loadStarships(this);
        System.out.println("WORKING ON MAIN-ACTIVITY");
        System.out.println(fleet);
    }

    private void dynamicAddShips(Fleet fleet) {
        // create layout object
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.rootLayout);

        ArrayList<Starship> fleetInfo = fleet.getStarshipArray();
        for (Starship ship : fleetInfo) {
            Button button = new Button(this);
            String btnText = ship.getName() + "\n" + ship.getRegistry();
            button.setText(btnText);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v(TAG, "Clicked on " + button.getText());
                    Toast.makeText(v.getContext(), "Clicked on: " + ship.getName(), Toast.LENGTH_LONG).show();
                    launchActivity(ship);
                }
            });

            button.setBackground(getResources().getDrawable(R.drawable.button_back));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


            layoutParams.setMargins(30, 30, 30, 0);
            layoutParams.height = 200;
            layoutParams.width = 700;
            button.setTextColor(Color.WHITE);

            button.setLayoutParams(layoutParams);

            rootLayout.addView(button);
        }
    }

    private void launchActivity(Starship ship) {
        Intent intent = new Intent(this, StarshipActivity.class);
        intent.putExtra(intentKey, ship);
        startActivity(intent);
    }

    public static String decodeIntent() {
        return intentKey;
    }


}