package hcmute.nhom03.foodyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodOrderViewHolder> {
    private LinkedList<Cart> carts;
    private LayoutInflater inflater;
    private Context context;
    private CartDao cartDao;

    public FoodOrderAdapter(Context context, LinkedList<Cart> carts) {
        this.carts = carts;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.cartDao = new CartDao(context);
    }

    class FoodOrderViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, price, quantity;
        public final ImageView image;
        public final ImageButton clearBtn;
        final FoodOrderAdapter adapter;

        public FoodOrderViewHolder(@NonNull View itemView, FoodOrderAdapter adapter) {
            super(itemView);
            this.name = itemView.findViewById(R.id.food_orderName);
            this.price = itemView.findViewById(R.id.food_orderPrice);
            this.quantity = itemView.findViewById(R.id.quantity_order);
            this.image = itemView.findViewById(R.id.food_orderImage);
            this.clearBtn = itemView.findViewById(R.id.btnClear);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public FoodOrderAdapter.FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = this.inflater.inflate(R.layout.food_order_item, parent, false);
        return new FoodOrderAdapter.FoodOrderViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderAdapter.FoodOrderViewHolder holder, int position) {
        Cart mCurrent = carts.get(position);
        holder.name.setText(mCurrent.getFood().getName());
        holder.price.setText(String.valueOf(mCurrent.getFood().getPrice()));
        holder.quantity.setText(String.valueOf(mCurrent.getQuantity()));
        Glide.with(context).load(mCurrent.getFood().getImage()).into(holder.image);

        holder.clearBtn.setOnClickListener(new View.OnClickListener() {
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
