package hogent.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity  {

    private static final String TAG = "Sudoku";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        //TODO: hier voegen we listeners toe voor alle buttons

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: vul de code aan om opties te selecteren
    }

    @Override
    public void onClick(View view) {
        //TODO : Start hier de relevante activities
    }

    private void openNewGameDialog(){
        //TO0DO: hier komt de code om een new game dialoog te maken
    }

    private void startGame(int i){
        Log.d(TAG, "Clicked on "+i);
        // TODO: vul hier de code aan om een activity te starten
    }
}
