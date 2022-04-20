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
import hcmute.nhom03.foodyapp.model.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private LinkedList<Food> foods;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, LinkedList<Food> foods) {
        this.inflater = LayoutInflater.from(context);
        this.foods = foods;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        public final TextView foodName, foodPrice;
        public final ImageView foodimage;
        final FoodAdapter adapter;


        public FoodViewHolder(@NonNull View itemView, FoodAdapter adapter) {
            super(itemView);
            this.foodName = itemView.findViewById(R.id.foodName);
            this.foodPrice = itemView.findViewById(R.id.foodPrice);
            this.foodimage = itemView.findViewById(R.id.foodImage);
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
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
