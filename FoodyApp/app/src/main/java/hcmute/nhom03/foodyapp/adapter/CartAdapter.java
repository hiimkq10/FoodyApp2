package hcmute.nhom03.foodyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.CartActivity;
import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.model.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private LinkedList<Cart> carts;
    private LayoutInflater inflater;
    private Context context;
    private CartDao cartDao;

    public CartAdapter(Context context, LinkedList<Cart> carts) {
        this.carts = carts;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.cartDao = new CartDao(context);
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, price, quantity;
        public final ImageView image;
        public final ImageButton addBtn, removeBtn;
        final CartAdapter adapter;

        public CartViewHolder(@NonNull View itemView, CartAdapter adapter) {
            super(itemView);
            this.name = itemView.findViewById(R.id.foodName);
            this.price = itemView.findViewById(R.id.foodPrice);
            this.quantity = itemView.findViewById(R.id.quantity);
            this.image = itemView.findViewById(R.id.foodImage);
            this.addBtn = itemView.findViewById(R.id.addButton);
            this.removeBtn = itemView.findViewById(R.id.removeButton);
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
        holder.name.setText(mCurrent.getFood().getName());
        holder.price.setText(String.valueOf(mCurrent.getFood().getPrice()));
        holder.quantity.setText(String.valueOf(mCurrent.getQuantity()));
        Glide.with(context).load(mCurrent.getFood().getImage()).into(holder.image);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrent.setQuantity(mCurrent.getQuantity() + 1);
                cartDao.updateCart(mCurrent);
                notifyDataSetChanged();
                ((CartActivity) context).UpdateCartBadge();
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrent.getQuantity() == 1) {
                    carts.remove(mCurrent);
                    cartDao.deleteCart(mCurrent);
                }
                else {
                    mCurrent.setQuantity(mCurrent.getQuantity() - 1);
                    cartDao.updateCart(mCurrent);
                }
                notifyDataSetChanged();
                ((CartActivity) context).UpdateCartBadge();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
