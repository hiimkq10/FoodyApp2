package hcmute.nhom03.foodyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import hcmute.nhom03.foodyapp.FoodInfoActivity;
import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.RestaurantInfoActivity;
import hcmute.nhom03.foodyapp.dao.CartDao;
import hcmute.nhom03.foodyapp.model.Cart;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.utils.Constants;
import hcmute.nhom03.foodyapp.utils.PreferenceManager;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private LinkedList<Food> foods;
    private LayoutInflater inflater;
    private CartDao cartDao;
    private Context context;
    private PreferenceManager preferenceManager;

    public FoodAdapter(Context context, LinkedList<Food> foods) {
        this.inflater = LayoutInflater.from(context);
        this.foods = foods;
        this.context = context;
        this.cartDao = new CartDao(context);
        this.preferenceManager = new PreferenceManager(context);
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        public final TextView foodName, foodPrice;
        public final ImageView foodimage;
        public final ImageButton addButton;
        final FoodAdapter adapter;


        public FoodViewHolder(@NonNull View itemView, FoodAdapter adapter) {
            super(itemView);
            this.foodName = itemView.findViewById(R.id.foodName);
            this.foodPrice = itemView.findViewById(R.id.foodPrice);
            this.foodimage = itemView.findViewById(R.id.foodImage);
            this.addButton = itemView.findViewById(R.id.addButton);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = this.inflater.inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        Food mCurrent = this.foods.get(position);
        holder.foodName.setText(mCurrent.getName());
        holder.foodPrice.setText(String.valueOf(mCurrent.getPrice()));
        holder.foodimage.setImageResource(mCurrent.getImage());
        if (!mCurrent.isDelivery()) {
            holder.addButton.setVisibility(View.GONE);
        }
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferenceManager.getString(Constants.KEY_USER_ID) != null) {
                    Cart cart = cartDao.getCartByFood(mCurrent);
                    if (cart != null) {
                        cart.setQuantity(cart.getQuantity() + 1);
                        cartDao.updateCart(cart);
                    }
                    else {
                        cart = new Cart(Integer.parseInt(preferenceManager.getString(Constants.KEY_USER_ID)), mCurrent, 1);
                        cartDao.insertCart(cart);
                    }
                    ((RestaurantInfoActivity) context).UpdateCartBadge();   
                }
                else {
                    Toast.makeText(context, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodInfoActivity.class);
                intent.putExtra("Food", mCurrent);
                intent.putExtra("Restaurant", ((RestaurantInfoActivity) context).GetRestaurant());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
