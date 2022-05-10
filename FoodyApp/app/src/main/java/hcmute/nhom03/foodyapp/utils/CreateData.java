package hcmute.nhom03.foodyapp.utils;

import android.database.Cursor;

import hcmute.nhom03.foodyapp.R;
import hcmute.nhom03.foodyapp.dao.FoodDao;
import hcmute.nhom03.foodyapp.dao.RestaurantDao;
import hcmute.nhom03.foodyapp.model.Food;
import hcmute.nhom03.foodyapp.model.Restaurant;

public class CreateData {

    public static void CreateRestaurantData(RestaurantDao restaurantDao) {

        //        Restaurant 1
        Restaurant restaurant1 = new Restaurant("Sorae Restaurant – Lounge",
                "Xuất hiện đầu tiên trong danh sách các nhà hàng sang trọng tại Sài Gòn là Sorae Restaurant – Lounge, một nhà hàng Nhật nổi tiếng ở TP. Hồ Chí Minh. Nằm ở tầng 24 và 25 AB Tower, Sorae Restaurant – Lounge có view tuyệt đẹp của trung tâm thành phố qua những ô kính lung linh bao quanh 4 mặt nhà hàng.",
                    false,
                    R.drawable.res1,
                    "17:30–23:00",
                    "AB Tower, 24th Floor, 76A Lê Lai, Phường Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh");
        restaurantDao.insertRestaurant(restaurant1);

        //        Restaurant 2
        Restaurant restaurant2 = new Restaurant("Moo Beef Steak Prime",
                "Nhà hàng Moo Beef Steak Prime lung linh, tráng lệ ngay từ cái nhìn đầu tiên. Moo Beef Steak Prime nằm ngay trung tâm quận 1 trên con đường Ngô Đức Kế sầm uất chỉ cách phố đi bộ vài bước chân.",
                false,
                R.drawable.res2,
                "18:00 - 22:00",
                "35 Ngô Đức Kế, Bến Nghé, Quận 1");
        restaurantDao.insertRestaurant(restaurant2);

        //        Restaurant 3
        Restaurant restaurant3 = new Restaurant("Shri",
                "Shri là một nhà hàng ở tầng 23 Centec Tower, vì thế Shri có view đẹp không cần bàn cãi. Hoạt động đã được vài năm, Shri vẫn giữ vững vị trí một trong những nhà hàng sang trọng tại Sài Gòn đáng để đến nhất. ",
                true,
                R.drawable.res3,
                "10:30 – 24:00",
                "72 Nguyễn Thị Minh Khai, Quận 3");
        restaurantDao.insertRestaurant(restaurant3);

        //        Restaurant 4
        Restaurant restaurant4 = new Restaurant("Social Club - Hotel des Art",
                "Social Club là nhà hàng nằm ở tầng 23 thuộc khách sạn MGallery Hotel des Art, vốn nổi tiếng là một trong những khách sạn có thiết kế sang chảnh và tinh tế nhất Sài Gòn.",
                false,
                R.drawable.res4,
                "10:30 – 24:00",
                "76 - 78 Nguyễn Thị Minh Khai, Quận 3");
        restaurantDao.insertRestaurant(restaurant4);

        //        Restaurant 5
        Restaurant restaurant5 = new Restaurant("Namo Pizza",
                "Ngay từ tên gọi bạn cũng nhận ra Namo là một nhà hàng Ý. Namo có hai chi nhánh ở Hai Bà Trưng và Pasteur. NAMO Artisanal Pizzeria ở Hai Bà Trưng thì có menu các món Ý đa dạng hơn.",
                true,
                R.drawable.res5,
                "11:00 – 23:00",
                "74/6 Hai Bà Trưng, Quận 1");
        restaurantDao.insertRestaurant(restaurant5);

        //        Restaurant 6
        Restaurant restaurant6 = new Restaurant("Pizza 4P’s",
                "Pizza 4P’s thì quá nổi tiếng đến không cần phải giới thiệu nhiều. Nếu bạn tìm một nơi có không gian đẹp, thức ăn ngon, giá cả lại hợp lý thì Pizza 4P’s là một lựa chọn hoàn hảo.",
                true,
                R.drawable.res6,
                "10:00 - 23:00",
                "8/15 Lê Thánh Tôn, Quận 1.");
        restaurantDao.insertRestaurant(restaurant6);

        //        Restaurant 7
        Restaurant restaurant7 = new Restaurant("El Gaucho Steakhouse",
                "El Gaucho Steakhouse được mệnh danh là nhà hàng chuyên về steak ngon nhất Sài Gòn. Nằm ngay trung tâm quận 1, nhà hàng có phong cách khá cool và sang trọng, vừa có không gian cho cặp đôi, vừa có những khu vực lớn cho gia đình.",
                true,
                R.drawable.res7,
                "11:00 - 23:30",
                "74 Hai Bà Trưng, Quận 1.");
        restaurantDao.insertRestaurant(restaurant7);

        //        Restaurant 8
        Restaurant restaurant8 = new Restaurant("EON 51",
                "Nằm tại tầng 51 của toà nhà Bitexco, EON 51 là nhà hàng cao nhất Sài Gòn và có view đẹp nhất nhì thành phố.",
                false,
                R.drawable.res8,
                "17:00 - 23:00",
                "Tầng 51 Bitexco Tower, 2 Hải Triều, Quận 1.");
        restaurantDao.insertRestaurant(restaurant8);

        //        Restaurant 9
        Restaurant restaurant9 = new Restaurant("The Log",
                "Sao có thể quên được một nơi lãng mạn như The Log trong danh sách các nhà hàng sang trọng tại Sài Gòn này? Nằm tại rooftop Gem center, The Log như ngôi nhà bằng gỗ ấm cúng.",
                false,
                R.drawable.res9,
                "18:30 - 23:30",
                "Rooftop, GEM Center, 8 Nguyễn Bỉnh Khiêm, Quận 1.");
        restaurantDao.insertRestaurant(restaurant9);

        //        Restaurant 10
        Restaurant restaurant10 = new Restaurant("L'olivier",
                "L'olivier là nhà hàng Pháp nằm tại khách sạn Sofitel Saigon - một khách sạn đẳng cấp từ Pháp. Có thể nói nếu bạn muốn tìm đúng hương vị ẩm thực Pháp cao cấp, thì L'olivier chính là nơi bạn nên đến.",
                false,
                R.drawable.res10,
                "18:30 - 22:30",
                "17 Lê Duẩn, Quận 1.");
        restaurantDao.insertRestaurant(restaurant10);

        //        Restaurant 11
        Restaurant restaurant11 = new Restaurant("Sorae Restaurant – Lounge 11",
                "Xuất hiện đầu tiên trong danh sách các nhà hàng sang trọng tại Sài Gòn là Sorae Restaurant – Lounge, một nhà hàng Nhật nổi tiếng ở TP. Hồ Chí Minh. Nằm ở tầng 24 và 25 AB Tower, Sorae Restaurant – Lounge có view tuyệt đẹp của trung tâm thành phố qua những ô kính lung linh bao quanh 4 mặt nhà hàng.",
                false,
                R.drawable.res11,
                "17:30–23:00",
                "AB Tower, 24th Floor, 76A Lê Lai, Phường Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh");
        restaurantDao.insertRestaurant(restaurant11);

        //        Restaurant 12
        Restaurant restaurant12 = new Restaurant("Moo Beef Steak Prime 12",
                "Nhà hàng Moo Beef Steak Prime lung linh, tráng lệ ngay từ cái nhìn đầu tiên. Moo Beef Steak Prime nằm ngay trung tâm quận 1 trên con đường Ngô Đức Kế sầm uất chỉ cách phố đi bộ vài bước chân.",
                false,
                R.drawable.res12,
                "18:00 - 22:00",
                "35 Ngô Đức Kế, Bến Nghé, Quận 1");
        restaurantDao.insertRestaurant(restaurant12);

        //        Restaurant 13
        Restaurant restaurant13 = new Restaurant("Shri 13",
                "Shri là một nhà hàng ở tầng 23 Centec Tower, vì thế Shri có view đẹp không cần bàn cãi. Hoạt động đã được vài năm, Shri vẫn giữ vững vị trí một trong những nhà hàng sang trọng tại Sài Gòn đáng để đến nhất. ",
                false,
                R.drawable.res13,
                "10:30 – 24:00",
                "72 Nguyễn Thị Minh Khai, Quận 3");
        restaurantDao.insertRestaurant(restaurant13);

        //        Restaurant 14
        Restaurant restaurant14 = new Restaurant("Social Club - Hotel des Art 14",
                "Social Club là nhà hàng nằm ở tầng 23 thuộc khách sạn MGallery Hotel des Art, vốn nổi tiếng là một trong những khách sạn có thiết kế sang chảnh và tinh tế nhất Sài Gòn.",
                false,
                R.drawable.res14,
                "10:30 – 24:00",
                "76 - 78 Nguyễn Thị Minh Khai, Quận 3");
        restaurantDao.insertRestaurant(restaurant14);

        //        Restaurant 15
        Restaurant restaurant15 = new Restaurant("Namo Pizza 15",
                "Ngay từ tên gọi bạn cũng nhận ra Namo là một nhà hàng Ý. Namo có hai chi nhánh ở Hai Bà Trưng và Pasteur. NAMO Artisanal Pizzeria ở Hai Bà Trưng thì có menu các món Ý đa dạng hơn.",
                false,
                R.drawable.res15,
                "11:00 – 23:00",
                "74/6 Hai Bà Trưng, Quận 1");
        restaurantDao.insertRestaurant(restaurant15);

        //        Restaurant 16
        Restaurant restaurant16 = new Restaurant("Pizza 4P’s 16",
                "Pizza 4P’s thì quá nổi tiếng đến không cần phải giới thiệu nhiều. Nếu bạn tìm một nơi có không gian đẹp, thức ăn ngon, giá cả lại hợp lý thì Pizza 4P’s là một lựa chọn hoàn hảo.",
                true,
                R.drawable.res16,
                "10:00 - 23:00",
                "8/15 Lê Thánh Tôn, Quận 1.");
        restaurantDao.insertRestaurant(restaurant16);

        //        Restaurant 17
        Restaurant restaurant17 = new Restaurant("El Gaucho Steakhouse 17",
                "El Gaucho Steakhouse được mệnh danh là nhà hàng chuyên về steak ngon nhất Sài Gòn. Nằm ngay trung tâm quận 1, nhà hàng có phong cách khá cool và sang trọng, vừa có không gian cho cặp đôi, vừa có những khu vực lớn cho gia đình.",
                true,
                R.drawable.res17,
                "11:00 - 23:30",
                "74 Hai Bà Trưng, Quận 1.");
        restaurantDao.insertRestaurant(restaurant17);

        //        Restaurant 8
        Restaurant restaurant18 = new Restaurant("EON 18",
                "Nằm tại tầng 51 của toà nhà Bitexco, EON 51 là nhà hàng cao nhất Sài Gòn và có view đẹp nhất nhì thành phố.",
                true,
                R.drawable.res18,
                "17:00 - 23:00",
                "Tầng 51 Bitexco Tower, 2 Hải Triều, Quận 1.");
        restaurantDao.insertRestaurant(restaurant18);

        //        Restaurant 19
        Restaurant restaurant19 = new Restaurant("The Log 19",
                "Sao có thể quên được một nơi lãng mạn như The Log trong danh sách các nhà hàng sang trọng tại Sài Gòn này? Nằm tại rooftop Gem center, The Log như ngôi nhà bằng gỗ ấm cúng.",
                false,
                R.drawable.res19,
                "18:30 - 23:30",
                "Rooftop, GEM Center, 8 Nguyễn Bỉnh Khiêm, Quận 1.");
        restaurantDao.insertRestaurant(restaurant19);

        //        Restaurant 20
        Restaurant restaurant20 = new Restaurant("L'olivier 20",
                "L'olivier là nhà hàng Pháp nằm tại khách sạn Sofitel Saigon - một khách sạn đẳng cấp từ Pháp. Có thể nói nếu bạn muốn tìm đúng hương vị ẩm thực Pháp cao cấp, thì L'olivier chính là nơi bạn nên đến.",
                true,
                R.drawable.res20,
                "18:30 - 22:30",
                "17 Lê Duẩn, Quận 1.");
        restaurantDao.insertRestaurant(restaurant20);
    }

    public static void CreateFoodData(RestaurantDao restaurantDao, FoodDao foodDao) {
        Cursor cursor = restaurantDao.getRestaurants();
        while (cursor.moveToNext()) {
            //            Food 1
            Food food1 = new Food();
            food1.setResID(cursor.getInt(0));
            food1.setName("Bánh Mì");
            food1.setPrice(20000);
            food1.setImage(R.drawable.food1);
            food1.setDescription("Món này ngon lắm");
            foodDao.insertFood(food1);

            //            Food 2
            Food food2 = new Food();
            food2.setResID(cursor.getInt(0));
            food2.setName("Xôi");
            food2.setPrice(20000);
            food2.setImage(R.drawable.food2);
            food2.setDescription("Món này ngon lắm");
            foodDao.insertFood(food2);

            //            Food 3
            Food food3 = new Food();
            food3.setResID(cursor.getInt(0));
            food3.setName("Ốc");
            food3.setPrice(50000);
            food3.setImage(R.drawable.food3);
            food3.setDescription("Món này ngon lắm");
            foodDao.insertFood(food3);

            //            Food 4
            Food food4 = new Food();
            food4.setResID(cursor.getInt(0));
            food4.setName("Bánh Canh");
            food4.setPrice(40000);
            food4.setImage(R.drawable.food4);
            food4.setDescription("Món này ngon lắm");
            foodDao.insertFood(food4);

            //            Food 5
            Food food5 = new Food();
            food5.setResID(cursor.getInt(0));
            food5.setName("Lẩu Thái");
            food5.setPrice(100000);
            food5.setImage(R.drawable.food5);
            food5.setDescription("Món này ngon lắm");
            foodDao.insertFood(food5);

            //            Food 6
            Food food6 = new Food();
            food6.setResID(cursor.getInt(0));
            food6.setName("Lẩu Bò");
            food6.setPrice(100000);
            food6.setImage(R.drawable.food6);
            food6.setDescription("Món này ngon lắm");
            foodDao.insertFood(food6);

            //            Food 7
            Food food7 = new Food();
            food7.setResID(cursor.getInt(0));
            food7.setName("Lẩu Dê");
            food7.setPrice(120000);
            food7.setImage(R.drawable.food7);
            food7.setDescription("Món này ngon lắm");
            foodDao.insertFood(food7);

            //            Food 8
            Food food8 = new Food();
            food8.setResID(cursor.getInt(0));
            food8.setName("Lẩu Cá");
            food8.setPrice(120000);
            food8.setImage(R.drawable.food8);
            food8.setDescription("Món này ngon lắm");
            foodDao.insertFood(food8);

            //            Food 9
            Food food9 = new Food();
            food9.setResID(cursor.getInt(0));
            food9.setName("Kem");
            food9.setPrice(30000);
            food9.setImage(R.drawable.food9);
            food9.setDescription("Món này ngon lắm");
            foodDao.insertFood(food9);

            //            Food 10
            Food food10 = new Food();
            food10.setResID(cursor.getInt(0));
            food10.setName("Chè");
            food10.setPrice(25000);
            food10.setImage(R.drawable.food10);
            food10.setDescription("Món này ngon lắm");
            foodDao.insertFood(food10);

        }
    }
}
