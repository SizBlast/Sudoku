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

public class PuzzleView extends View {

    private static final String TAG = "Sudoku.view";

    private final Game game;

    private int tileWidth;
    private int tileHeight;
    private int cursorX;
    private int cursorY;

    // Paint objecten
    private Paint background;
    private Paint foreground;
    private Paint highlight;
    private Paint light;
    private Paint dark;
    private Paint selected;

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
        background = new Paint();
        background.setColor(getResources().getColor(R.color.puzzle_background));

        dark = new Paint(Paint.ANTI_ALIAS_FLAG);
        dark.setColor(getResources().getColor(R.color.puzzle_dark));

        highlight = new Paint(Paint.ANTI_ALIAS_FLAG);
        highlight.setColor(getResources().getColor(R.color.puzzle_highlight));

        light = new Paint(Paint.ANTI_ALIAS_FLAG);
        light.setColor(getResources().getColor(R.color.puzzle_light));

        foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextAlign(Paint.Align.CENTER);

        selected = new Paint();
        selected.setColor(getResources().getColor(R.color.puzzle_selected));

        cursorX = 0;
        cursorY = 0;

        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Berekenen van de grootte van de view
        int h = measureHeight(heightMeasureSpec);
        int w = measureWidth(widthMeasureSpec);

        if (getResources().getConfiguration().orientation == 1)
        {
            h = measureHeight(widthMeasureSpec);
            w = measureWidth(widthMeasureSpec);
        }
        else if (getResources().getConfiguration().orientation == 2)
        {
            h = measureHeight(heightMeasureSpec);
            w = measureWidth(heightMeasureSpec);
        }
        getRect(cursorX*(w/tileWidth),cursorY*(h/tileHeight),selRect);
        setMeasuredDimension(w,h);
    }

    private void getRect(int x, int y, Rect rect){
        rect.set((int)(x*tileWidth+1),(int)(y*tileHeight+1),(int)(x*tileWidth+tileWidth-2),(int)(y*tileHeight+tileHeight-2));
    }

    private int measureHeight(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = specSize;
        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 500;
        } else {
            result = specSize;
        }
        tileHeight = specSize/9;
        return result;
    }

    private int measureWidth(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = specSize;
        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 500;
        } else {
            result = specSize;
        }
        tileWidth = specSize/9;
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(),getHeight(), background);

        for(int i = 0; i< 9; i++)
        {
            if (i != 3 || i != 6)
            {
                // Minor gridlines
                canvas.drawLine(0, i * tileHeight, getWidth(), i * tileHeight, light);
                canvas.drawLine(0, i * tileHeight + 1, getWidth(), i * tileHeight + 1, highlight);
                canvas.drawLine(i * tileWidth, 0, i * tileWidth, getHeight(), light);
                canvas.drawLine(i * tileWidth + 1, 0, i * tileWidth + 1, getHeight(), highlight);
            }
            if (i == 3 || i == 6)
            {
                // Major gridlines
                canvas.drawLine(0, i * tileHeight, getWidth(), i * tileHeight, dark);
                canvas.drawLine(0, i * tileHeight + 1, getWidth(), i * tileHeight + 1, highlight);
                canvas.drawLine(i * tileWidth, 0, i * tileWidth, getHeight(), dark);
                canvas.drawLine(i * tileWidth + 1, 0, i * tileWidth + 1, getHeight(), highlight);
            }
        }

        canvas.drawRect(selRect,selected);

        // Teken de nummers
        Paint.FontMetrics fm = foreground.getFontMetrics();
        foreground.setTextSize(tileHeight * 0.6f);
        foreground.setTextScaleX(tileWidth / tileHeight);
        float x = tileWidth/2;
        float y = tileHeight / 2 - (fm.ascent + fm.descent) / 2;
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                canvas.drawText(this.game.getTileString(i, j),i*tileWidth+x,j*tileHeight+y,foreground);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_DOWN:
                select(cursorX, cursorY + 1);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                select(cursorX + 1, cursorY);
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                select(cursorX, cursorY - 1);
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                select(cursorX - 1, cursorY);
                return true;
            case KeyEvent.KEYCODE_0:
                setSelectedTile(0);
                return true;
            case KeyEvent.KEYCODE_1:
                setSelectedTile(1);
                return true;
            case KeyEvent.KEYCODE_2:
                setSelectedTile(2);
                return true;
            case KeyEvent.KEYCODE_3:
                setSelectedTile(3);
                return true;
            case KeyEvent.KEYCODE_4:
                setSelectedTile(4);
                return true;
            case KeyEvent.KEYCODE_5:
                setSelectedTile(5);
                return true;
            case KeyEvent.KEYCODE_6:
                setSelectedTile(6);
                return true;
            case KeyEvent.KEYCODE_7:
                setSelectedTile(7);
                return true;
            case KeyEvent.KEYCODE_8:
                setSelectedTile(8);
                return true;
            case KeyEvent.KEYCODE_9:
                setSelectedTile(9);
                return true;
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                game.showKeypadOrError(cursorX, cursorY);
                break;
            default:
                return super.onKeyDown(keyCode,event);
        }
        return false;
    }

    public void setSelectedTile(int tile) {
        if (game.setTileIfValid(cursorX, cursorY, tile)){
            invalidate(); // may change hints
        } else {
            // Number is not valid for this tile
            Log.d(TAG, "setSelectedTile: invalid: " + tile);
        }
    }

    private void select(int x, int y){
        invalidate(selRect);
        cursorY = Math.min(Math.max(y,0), 8);
        cursorX = Math.min(Math.max(x,0), 8);

        getRect(cursorX,cursorY,selRect);
        invalidate(selRect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);
        select((int) (event.getX() / tileWidth),
                (int) (event.getY() / tileHeight));
        game.showKeypadOrError(cursorX, cursorY);
        Log.d(TAG, "onTouchEvent: x " + cursorX + ", y " + cursorY);
        return true;
    }
}
