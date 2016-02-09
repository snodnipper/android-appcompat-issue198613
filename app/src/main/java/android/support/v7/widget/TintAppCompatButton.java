package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.util.AttributeSet;

import uk.co.snodnipper.android.issue198613.tintdrawables.R;

public class TintAppCompatButton extends android.support.v7.widget.AppCompatButton {

    private static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;

    public TintAppCompatButton(Context context) {
        super(context);
    }

    public TintAppCompatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public TintAppCompatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        boolean isRequired = android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M;
        if (!isRequired) {
            return;
        }

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TintAppCompatButton,
                defStyleAttr, 0);

        try {
            PorterDuff.Mode tintMode = DrawableUtils.parseTintMode(
                    a.getInt(R.styleable.TintAppCompatButton_drawableTintMode, -1),
                    DEFAULT_TINT_MODE);

            boolean hasColor = a.hasValue(R.styleable.TintAppCompatButton_drawableTint);
            if (hasColor) {
                int color = a.getColor(R.styleable.TintAppCompatButton_drawableTint,
                        Color.TRANSPARENT);

                Drawable[] ds = getCompoundDrawables();
                tint(ds[LEFT], color, tintMode);
                tint(ds[TOP], color, tintMode);
                tint(ds[RIGHT], color, tintMode);
                tint(ds[BOTTOM], color, tintMode);
            }
        } finally {
            a.recycle();
        }
    }

    private void tint(Drawable d, int color, PorterDuff.Mode tintMode) {
        boolean isTintable = d != null && tintMode != null;
        if (!isTintable) {
            return;
        }
        TintInfo ti = new TintInfo();
        ti.mTintMode = tintMode;
        ti.mTintList = ColorStateList.valueOf(color);
        ti.mHasTintList = true;
        ti.mHasTintMode = true;

        TintManager.tintDrawable(d, ti, new int[]{0});
    }
}
