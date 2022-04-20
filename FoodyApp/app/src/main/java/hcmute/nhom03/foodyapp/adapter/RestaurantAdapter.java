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
import hcmute.nhom03.foodyapp.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private final LinkedList<Restaurant> restaurants;
    private LayoutInflater inflater;

    public RestaurantAdapter(Context context, LinkedList<Restaurant> restaurants) {
        this.inflater = LayoutInflater.from(context);
        this.restaurants = restaurants;
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public final ImageView restaurantImage;
        public final TextView delivery, restaurantName, restaurantDescription;
        final RestaurantAdapter adapter;

        public RestaurantViewHolder(@NonNull View itemView, RestaurantAdapter adapter) {
            super(itemView);
            this.restaurantImage = itemView.findViewById(R.id.restaurantImage);
            this.delivery = itemView.findViewById(R.id.delivery);
            this.restaurantName = itemView.findViewById(R.id.restaurantName);
            this.restaurantDescription = itemView.findViewById(R.id.restaurantDescription);
            this.adapter = adapter;
        }
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = this.inflater.inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restaurant mCurrent = this.restaurants.get(position);
        holder.restaurantName.setText(mCurrent.getName());
        holder.restaurantDescription.setText(mCurrent.getDescription());
        holder.restaurantImage.setImageResource(mCurrent.getImage());
        if (!mCurrent.getDelivery()) {
            holder.delivery.setText("Review");
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
