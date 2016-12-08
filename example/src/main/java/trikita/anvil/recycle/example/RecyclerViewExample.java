package trikita.anvil.recycle.example;

import static trikita.anvil.DSL.checkBox;
import static trikita.anvil.DSL.text;
import static trikita.anvil.DSL.textView;

import trikita.anvil.RenderableView;
import trikita.anvil.recyclerview.Recycler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExample extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(new SimpleList(this));
    }

    private static List<Object> items() {
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add("Item #" + i);
        }
        for (int i = 0; i < 50; i++) {
            items.add(i * 2, i * 200);
        }
        return items;
    }

    public static class SimpleList extends RenderableView {
        private final List<Object> mItems = items();

        public SimpleList(Context c) {
            super(c);
        }

        public void view() {
            Recycler.view(() -> {
                Recycler.layoutManager(
                        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                Recycler.hasFixedSize(true);
                Recycler.itemAnimator(new DefaultItemAnimator());
                Recycler.adapter(Recycler.Adapter.simple(mItems, (viewHolder, viewType) -> {
                    if (viewType == String.class.hashCode()) {
                        textView(() -> {
                            text((String) mItems.get(viewHolder.getAdapterPosition()));
                        });
                    } else {
                        checkBox(() -> {
                            text((mItems.get(viewHolder.getAdapterPosition())).toString());
                        });
                    }
                }));
            });
        }
    }
}

