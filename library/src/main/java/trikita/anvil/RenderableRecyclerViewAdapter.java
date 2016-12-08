package trikita.anvil;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class RenderableRecyclerViewAdapter
        extends RecyclerView.Adapter<RenderableRecyclerViewAdapter.MountHolder> {

    @Override
    public MountHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        FrameLayout root = new FrameLayout(parent.getContext());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MountHolder(root);
    }

    @Override
    public void onBindViewHolder(final MountHolder holder, int position) {
        if (holder.mount == null) {
            holder.mount = new Anvil.Mount(holder.itemView, new Anvil.Renderable() {
                public void view() {
                    RenderableRecyclerViewAdapter.this.view(
                            holder,
                            getItemViewType(holder.getAdapterPosition()));
                }
            });
        }
        Anvil.render(holder.mount);
    }

    static class MountHolder extends RecyclerView.ViewHolder {
        private Anvil.Mount mount;

        MountHolder(ViewGroup itemView) {
            super(itemView);
        }
    }

    public abstract void view(RecyclerView.ViewHolder holder, int viewType);
}

