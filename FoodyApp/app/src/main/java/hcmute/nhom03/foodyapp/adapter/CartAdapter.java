package hcmute.nhom03.foodyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private LinkedList<Cart> carts;
    private LayoutInflater inflater;

    public CartAdapter(Context context, LinkedList<Cart> carts) {
        this.carts = carts;
        this.inflater = LayoutInflater.from(context);
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, price, quantity;
        public final ImageView image;
        final CartAdapter adapter;

        public CartViewHolder(@NonNull View itemView, CartAdapter adapter) {
            super(itemView);
            this.name = itemView.findViewById(R.id.foodName);
            this.price = itemView.findViewById(R.id.foodPrice);
            this.quantity = itemView.findViewById(R.id.quantity);
            this.image = itemView.findViewById(R.id.foodImage);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = this.inflater.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Cart mCurrent = carts.get(position);
        holder.name.setText(mCurrent.getName());
        holder.price.setText(String.valueOf(mCurrent.getPrice()));
        holder.quantity.setText(mCurrent.getQuantity());
        holder.image.setImageResource(mCurrent.getImage());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
