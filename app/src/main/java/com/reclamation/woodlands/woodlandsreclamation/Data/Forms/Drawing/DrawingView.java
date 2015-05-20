package com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/19/2015.
 */
public class DrawingView extends View {

    private Path drawPath;

    private Paint drawPaint, canvasPaint;

    private int paintColor = 0xFF660000;

    private Canvas drawCanvas;

    private Bitmap canvasBitmap;

    private float brushSize, lastBrushSize;

    private boolean erase = false;

    private boolean hasBitmap = false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupDrawing();
    }



    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);


    }

    public void drawText(String gText, Canvas canvas){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(61, 61, 61));
        // text size in pixels
        paint.setTextSize((int) (14 * 1.5));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        // draw text to the Canvas center
        Rect bounds = new Rect();

        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (canvasBitmap.getWidth() - bounds.width())/2;
        int y = (canvasBitmap.getHeight() + bounds.height())/2;

        canvas.drawText(gText, x, y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;

            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    private void setupDrawing(){
        //get drawing area setup for interaction

        drawPath = new Path();
        drawPaint = new Paint();

        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;

        drawPaint.setColor(paintColor);

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(!hasBitmap) {
            canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }else{
            canvasBitmap = canvasBitmap.copy(Bitmap.Config.ARGB_8888, true);
        }
        drawCanvas = new Canvas(canvasBitmap);
    }

    public void setColor(String newColor){
        invalidate();

        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setBrushSize(float newSize){
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());

        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }

    public float getLastBrushSize(){
        return lastBrushSize;
    }

    public void setErase(boolean isErase){
        erase = isErase;

        if(erase){
            drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        }else{
            drawPaint.setXfermode(null);
        }
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void setCanvasBitmap(String path){
        canvasBitmap = BitmapFactory.decodeFile(path);
        hasBitmap = true;
    }
}
