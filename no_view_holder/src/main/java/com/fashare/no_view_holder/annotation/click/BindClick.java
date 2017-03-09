package com.fashare.no_view_holder.annotation.click;

import android.support.annotation.IdRes;
import android.view.View;

import com.fashare.no_view_holder.IBehavior;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by apple on 17-3-10.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface BindClick {
    @IdRes int id();

    class Behavior extends IBehavior.Simple<BindClick, View.OnClickListener>{
        public Behavior() {
            super(BindClick.class, View.OnClickListener.class);
        }

        @Override
        public void onBind(View itemView, BindClick annotation, final View.OnClickListener value) {
            final View view = itemView.findViewById(annotation.id());
            bindIfNotNull(view, annotation.id(), new Runnable() {
                @Override
                public void run() {
                    view.setOnClickListener(value);
                }
            });
        }
    }
}