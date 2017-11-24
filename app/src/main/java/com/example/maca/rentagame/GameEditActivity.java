package com.example.maca.rentagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maca.rentagame.model.Game;
import com.example.maca.rentagame.utils.GameListAdapter;

/**
 * Created by Maca on 11/24/2017.
 */

public class GameEditActivity extends AppCompatActivity
{
    private static final String TAG = GameEditActivity.class.getSimpleName();

    private GameListAdapter adapter;
    private Game game;

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
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
