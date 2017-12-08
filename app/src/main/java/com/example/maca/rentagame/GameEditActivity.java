package com.example.maca.rentagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maca.rentagame.model.Game;
import com.example.maca.rentagame.utils.DatePickerFragment;
import com.example.maca.rentagame.utils.GameListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.maca.rentagame.MainActivity.DIALOG_DATE;

/**
 * Created by Maca on 11/24/2017.
 */

public class GameEditActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener
{
    private static final String TAG = GameEditActivity.class.getSimpleName();

    private Game game;

    public final static String REQ_CODE_PICKER = "GETDATA";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        final EditText yearEditText = (EditText) findViewById(R.id.yearEditText);
        final EditText producerEditText = (EditText) findViewById(R.id.producerEditText);

        Intent intent = getIntent();

        game = (Game) intent.getSerializableExtra("GAME");

        nameEditText.setText(game.getName());
        yearEditText.setText(game.getReleaseYear());
        producerEditText.setText(game.getProducer());

        Button sendMailButton = (Button) findViewById(R.id.sendButton);

        sendMailButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "macajouu@yahoo.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Added a game with name :" + game.getName() + ", release year: " + game.getReleaseYear() + ", producer: " + game.getProducer());
                startActivity(Intent.createChooser(intent, "Send email"));
            }
        });

        Log.v(TAG, "OUTSIDE");
        Button updateGameButton = (Button) findViewById(R.id.updateButton);

        updateGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = nameEditText.getText().toString();
                String year = yearEditText.getText().toString();
                String producer = producerEditText.getText().toString();

                game.setName(name);
                game.setReleaseYear(year);
                game.setProducer(producer);

                Intent updatedIntent = new Intent();
                updatedIntent.putExtra("UPDATED_GAME", game);

                setResult(1, updatedIntent);
                finish();
            }
        });

        Button datePickerAlertDialog = (Button)findViewById(R.id.alert_dialog_date_picker);
        datePickerAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "START");

                DatePickerFragment dialog = new DatePickerFragment();
                Bundle bundle = new Bundle();

                Log.v(TAG, "Here NOW");

                Date date = new Date(game.getReleaseYear());

                Log.v(TAG, "Date: " +  date);

                bundle.putSerializable(REQ_CODE_PICKER, date);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), DIALOG_DATE);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public void onFinishDialog(Date date) {
        game.setReleaseYear(String.valueOf(date.getYear()));
        Toast.makeText(this, "Selected Date :"+ formatDate(date), Toast.LENGTH_SHORT).show();
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }
}
