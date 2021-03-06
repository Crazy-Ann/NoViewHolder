package com.fashare.no_view_holder.annotation;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fashare.no_view_holder.IBehavior;
import com.fashare.no_view_holder.widget.NoListViewAdapter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by apple on 16-11-19.
 *
 * Binder for ListView
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface BindListView {
    @IdRes int id();

    @LayoutRes int layout();

    class Behavior extends IBehavior.Simple<BindListView, ListView, List>{
        public Behavior() {
            super(BindListView.class, List.class);
        }

        @Override
        protected int getId(BindListView annotation) {
            return annotation.id();
        }

        @Override
        protected void onInitView(ListView targetView, BindListView annotation, List value) {
            if(value == null)
                value = new ArrayList();

            targetView.setAdapter(new NoListViewAdapter<>(targetView.getContext(), annotation.layout(), value));
        }

        @Override
        protected void onBind(ListView targetView, BindListView annotation, List value) {
            if(value == null)
                value = new ArrayList();

            BaseAdapter adapter = (BaseAdapter)targetView.getAdapter();
            if(adapter instanceof NoListViewAdapter)
                ((NoListViewAdapter) targetView.getAdapter()).setDataList(value);
            else
                adapter.notifyDataSetChanged();
        }
    }
}
