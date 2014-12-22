package hogent.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jbuy519 on 19/08/2014.
 */
public class PuzzleView extends View {

    private static final String TAG = "Sudoku.view";

    private final Game game;

    private int tileWidth;
    private int tileHeight;
    private int cursorX;
    private int cursorY;

    //TODO: Definieer hier uw paint objecten


    private final Rect selRect = new Rect();

    public PuzzleView(Context context) {
        super(context);
        this.game = (Game)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        init();
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.game = (Game)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        init();
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.game = (Game)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        init();
    }

    public void init(){
        //Haal passende referenties uit de views

        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //TODO: vul passende code in
    }

    private void getRect(int x, int y, Rect rect){
        //TODO
    }

    private int measureHeight(int measureSpec){
        //TODO
    }

    private int measureWidth(int measureSpec){
        //TODO
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //TODO: vul de codes in bij passende key down events, om de cursor te verplaatsen
    }

    public void setSelectedTile(int tile) {
        if (game.setTileIfValid(cursorX, cursorY, tile)){
            invalidate();// may change hints
        } else {
        // Number is not valid for this tile
            Log.d(TAG, "setSelectedTile: invalid: " + tile);
        }
    }

    private void select(int x, int y){
        //Is heel belangrijk!
       //TODO vul de code aan om te nieuwe x en y coordinaten te
        //bepalen om de nieuw geselecteerde rechthoek te bepalen
        // GEBRUIK INVALIDATE
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
          //TODO: te implementeren
    }


}
