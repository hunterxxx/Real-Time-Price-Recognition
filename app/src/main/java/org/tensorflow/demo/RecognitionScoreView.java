/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import org.tensorflow.demo.Classifier.Recognition;

import java.util.List;

public class RecognitionScoreView extends View implements ResultsView {
    private static final float TEXT_SIZE_DIP = 24;
    private List<Recognition> results;
    private final float textSizePx;
    private final Paint fgPaint;
    private final Paint bgPaint;
    Context context;

    public RecognitionScoreView(final Context context, final AttributeSet set) {
        super(context, set);

        this.context=context;

        textSizePx =
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        fgPaint = new Paint();
        fgPaint.setTextSize(textSizePx);

        bgPaint = new Paint();
        bgPaint.setColor(0xccf36b36); //#f36b36
    }

    @Override
    public void setResults(final List<Recognition> results) {
        this.results = results;
        postInvalidate();

    }

    @Override
    public void onDraw(final Canvas canvas) {
        int xPos = (canvas.getWidth() / 2);
        final int x = xPos;
        int y = (int) (fgPaint.getTextSize() * 1.5f);

        canvas.drawPaint(bgPaint);


        if (results != null) {
            for (final Recognition recog : results) {
                if (recog.getTitle().equals("macbook") && recog.getConfidence() > 0.6) {
                    //canvas.drawText("Macbook : 799 € - 1098 €", x, y, fgPaint);

                    TextView titleLabel = (TextView) ((Activity)context).findViewById(R.id.title);
                    TextView priceLabel = (TextView) ((Activity)context).findViewById(R.id.price);

                    titleLabel.setText("Apple Macbook 12-inch : ");
                    priceLabel.setText("799 € - 1098 €");

                } else if (recog.getTitle().equals("windows") && recog.getConfidence() > 0.6) {
                    //canvas.drawText( "Windows : 434 € - 747 €", x, y, fgPaint);

                    TextView titleLabel = (TextView) ((Activity)context).findViewById(R.id.title);
                    TextView priceLabel = (TextView) ((Activity)context).findViewById(R.id.price);

                    titleLabel.setText("Samsung Ultrabook NP530U3C: ");
                    priceLabel.setText("434 € - 747 €");
                }
                y += fgPaint.getTextSize() * 1.5f;
            }
        }
    }
}
