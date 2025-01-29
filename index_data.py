from elasticsearch import Elasticsearch
from elasticsearch.helpers import bulk

# Connect to Elasticsearch
es = Elasticsearch("http://localhost:9200")

# Define index names
index_category = "category"
index_product = "product"
index_product_variant = "product_variant"

# Define index mappings
mappings = {
    index_category: {
        "mappings": {
            "properties": {
                "id": {"type": "integer"},
                "name": {"type": "text"}
            }
        }
    },
    index_product: {
        "mappings": {
            "properties": {
                "id": {"type": "integer"},
                "name": {"type": "text"},
                "description": {"type": "text"},
                "category_id": {"type": "integer"},
                "image_url": {"type": "text"}
            }
        }
    },
    index_product_variant: {
        "mappings": {
            "properties": {
                "id": {"type": "integer"},
                "image1": {"type": "text"},
                "image2": {"type": "text"},
                "image3": {"type": "text"},
                "description": {"type": "text"},
                "price": {"type": "integer"},
                "sku": {"type": "keyword"},
                "product_id": {"type": "integer"}
            }
        }
    }
}

# Create indices
for index_name, mapping in mappings.items():
    if not es.indices.exists(index=index_name):
        es.indices.create(index=index_name, body=mapping)
        print(f"Index '{index_name}' created successfully!")
    else:
        print(f"Index '{index_name}' already exists.")

# Data for the indices
categories = [
    {"id": 1, "name": "Electronics"},
    {"id": 2, "name": "Mobile"},
    {"id": 3, "name": "Mobile Accessories"},
    {"id": 4, "name": "Laptop"},
    {"id": 5, "name": "TV"},
    {"id": 6, "name": "Gaming"}
]

products = [
    {"id": 1, "name": "Samsung Galaxy M15 5G Prime Edition", "description": "Samsung Galaxy M15 5G Prime Edition", "category_id": 1, "image_url": "https://m.media-amazon.com/images/I/81scQ7qlPuL._SX679_.jpg"},
    {"id": 2, "name": "Samsung Galaxy A35 5G", "description": "Samsung Galaxy A35 5G (Thunder Grey,8GB RAM,256GB Storage)| Corning Gorilla Glass Victus+| 6000mAh Battery | 120Hz Super AMOLED Display| Without Charger", "category_id": 1, "image_url": "https://m.media-amazon.com/images/I/41joXZ9kKSL._AC_.jpg"},
    {"id": 3, "name": "Samsung 55-inch Crystal UHD 4K Smart TV", "description": "Samsung 55-inch Crystal UHD 4K Smart TV with Dynamic Crystal Color, HDR 10+, Alexa Built-in, and a sleek bezel-less design. Perfect for a cinematic viewing experience at home.", "category_id": 4, "image_url": "https://m.media-amazon.com/images/I/81wmp2b-ZAL._AC_SL1500_.jpg"},
    {"id": 4, "name": "PlayStation 5 Console", "description": "PlayStation 5 Console with DualSense Wireless Controller, 825GB SSD, 8K Gaming Support, Ray Tracing, and Adaptive Triggers for an immersive gaming experience.", "category_id": 5, "image_url": "https://m.media-amazon.com/images/I/51BJqgjkNRL._SX522_.jpg"},
    {"id": 5, "name": "Apple MacBook Air M2", "description": "Apple MacBook Air 13.6-inch (2023) with M2 chip, 8GB RAM, 256GB SSD, Retina Display, and macOS Ventura.", "category_id": 3, "image_url": "https://m.media-amazon.com/images/I/61zRc9Q1jCL._AC_SX679_.jpg"},
    {"id": 6, "name": "Anker Fast Charger & Wireless Earbuds", "description": "Anker PowerPort 20W Fast Charger and Soundcore Life P2 Wireless Earbuds. Features rapid charging technology, crystal-clear audio, ergonomic design, and long-lasting battery life.", "category_id": 2, "image_url": "https://m.media-amazon.com/images/I/61Xfa0LDh6L._AC_SL1500_.jpg"},
    {"id": 10, "name": "Apple AirPods Pro", "description": "Apple AirPods Pro with Active Noise Cancellation, Transparency Mode, and Spatial Audio for immersive sound.", "category_id": 2, "image_url": "https://m.media-amazon.com/images/I/618kmr6+uoL._AC_SY300_SX300_.jpg"},
    {"id": 17, "name": "Apple MacBook Pro M1 Pro", "description": "Apple MacBook Pro with M1 Pro chip, 16GB RAM, 512GB SSD, and 14-inch Retina display. Ideal for professionals and creatives.", "category_id": 3, "image_url": "https://m.media-amazon.com/images/I/61cCf94xIEL._AC_SL1500_.jpg"},
    {"id": 20, "name": "Sony X900H 65-inch 4K LED TV", "description": "Sony X900H 65-inch 4K LED TV with Dolby Vision, Dolby Atmos, and Android TV for streaming apps and gaming.", "category_id": 4, "image_url": "https://m.media-amazon.com/images/I/81889-KTklL._AC_SL1500_.jpg"},
    {"id": 26, "name": "PlayStation VR Headset", "description": "PlayStation VR Headset with 3D audio, enhanced tracking, and immersive VR gameplay for PlayStation 4 and PlayStation 5.", "category_id": 5, "image_url": "https://m.media-amazon.com/images/I/71OVwWVSjTL.__AC_SX300_SY300_QL70_FMwebp_.jpg"},
    {"id": 27, "name": "Logitech G Pro X Gaming Headset", "description": "Logitech G Pro X Gaming Headset with Blue VO!CE technology, 50mm drivers, and advanced sound personalization for professional gamers.", "category_id": 6, "image_url": "https://m.media-amazon.com/images/I/71c+YOxfTGL._AC_SL1500_.jpg"}
]

product_variants = [
    {"id": 1, "image1": "https://m.media-amazon.com/images/I/81LJaRu0cZL._SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/81o0TdRrbZL._SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/81IdHFx4LJL._SX679_.jpg", "description": "Galaxy M15 5G Prime Edition (Green Topaz, 6GB RAM, 128GB Storage)", "price": 10999, "sku": "M15_6GB_128GB_GREEN", "product_id": 1},
    {"id": 2, "image1": "https://m.media-amazon.com/images/I/81scQ7qlPuL._SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/81o0TdRrbZL._SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/81IdHFx4LJL._SX679_.jpg", "description": "Galaxy M15 5G Prime Edition (Blue, 6GB RAM, 128GB Storage)", "price": 10999, "sku": "M15_6GB_128GB_BLUE", "product_id": 1},
    {"id": 3, "image1": "https://m.media-amazon.com/images/I/81scQ7qlPuL._SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/81o0TdRrbZL._SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/715LkSoQKrL._SL1500_.jpg", "description": "Galaxy M15 5G Prime Edition (Blue Topaz, 8GB RAM, 128GB Storage)", "price": 14999, "sku": "M15_8GB_256GB_BLUE", "product_id": 1},
    {"id": 4, "image1": "https://m.media-amazon.com/images/I/61Xfa0LDh6L._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/71X1BBIqp1L._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/71IX3M22ArL._AC_SL1500_.jpg", "description": "Anker PowerPort 20W Fast Charger (BLUE)", "price": 499, "sku": "Anker_BLUE", "product_id": 6},
    {"id": 5, "image1": "https://m.media-amazon.com/images/I/61lxflVlHGL._AC_SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/71LJvw9VWYL._AC_SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/71S2x7LrNgL._AC_SX679_.jpg", "description": "Anker PowerPort 20W Fast Charger (BLACK)", "price": 449, "sku": "Anker_BLACK", "product_id": 6},
    {"id": 6, "image1": "https://m.media-amazon.com/images/I/41joXZ9kKSL._AC_.jpg", "image2": "https://m.media-amazon.com/images/I/51doQ0a7I6L._AC_SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/51OMhwf01KL._AC_SX679_.jpg", "description": "Samsung Galaxy A35 5G (Thunder Green,6GB RAM,256GB Storage)", "price": 19999, "sku": "A35_6GB_128GB_Green", "product_id": 2},
    {"id": 7, "image1": "https://m.media-amazon.com/images/I/61Fw4Dj+HsL._AC_SY300_SX300_.jpg", "image2": "https://m.media-amazon.com/images/I/41lbmrtoKOL._AC_SY879_.jpg", "image3": "https://m.media-amazon.com/images/I/61A5SIuka4L._AC_SY879_.jpg", "description": "Samsung Galaxy A35 5G (Thunder Black,8GB RAM,256GB Storage)", "price": 24999, "sku": "A35_8GB_128GB_Black", "product_id": 2},
    {"id": 8, "image1": "https://m.media-amazon.com/images/I/618kmr6+uoL._AC_SY300_SX300_.jpg", "image2": "https://m.media-amazon.com/images/I/511Bd50qYaL._AC_SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/61+1hlL8L7L._AC_SX679_.jpg", "description": "Apple AirPods Pro with Active Noise Cancellation, Transparency Mode, and Spatial Audio for immersive sound.", "price": 25999, "sku": "3Gen_Airpod", "product_id": 10},
    {"id": 9, "image1": "https://m.media-amazon.com/images/I/61zRc9Q1jCL._AC_SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/61jIJc4AZ2L._AC_SL1200_.jpg", "image3": "https://m.media-amazon.com/images/I/41TBiOMU8UL._AC_SX679_.jpg", "description": "Apple MacBook Air 13.6-inch (2023) with M2 chip, 8GB RAM, 256GB SSD, Retina Display, and macOS Ventura.", "price": 79999, "sku": "Apple_M2_Gray", "product_id": 5},
    {"id": 10, "image1": "https://m.media-amazon.com/images/I/61ME8jRwbVL._AC_SX679_.jpg", "image2": "https://m.media-amazon.com/images/I/71bpuEep46L._AC_SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/51pwj0lWvzL._SP693,511,0%7C51h+jH-8lQL.jpg,51d34-8eTfL.jpg,51J8Qm4ni2L.jpg,51CB+qfedIL.jpg,51AdvLNpjEL.jpg_.jpg", "description": "Apple MacBook Air 13.6-inch (2023) Golden with M2 chip, 8GB RAM, 256GB SSD, Retina Display, and macOS Ventura.", "price": 79999, "sku": "Apple_M2_Golden", "product_id": 5},
    {"id": 11, "image1": "https://m.media-amazon.com/images/I/61cCf94xIEL._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/81b4e7f9SnL._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/812bneVWgKL._AC_SL1500_.jpg", "description": "Apple MacBook Pro (Silver) with M1 Pro chip, 16GB RAM, 512GB SSD, and 14-inch Retina display.", "price": 69999, "sku": "Apple_M1_Silver", "product_id": 17},
    {"id": 12, "image1": "https://m.media-amazon.com/images/I/61vFO3R5UNL._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/61vFO3R5UNL._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/61JqllsE+DL._AC_SL1500_.jpg", "description": "Apple MacBook Pro (Gray) with M1 Pro chip, 16GB RAM, 512GB SSD, and 14-inch Retina display.", "price": 69999, "sku": "Apple_M1_Gray", "product_id": 17},
    {"id": 13, "image1": "https://m.media-amazon.com/images/I/81wmp2b-ZAL._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/815DKZSgSEL._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/812iqCr1PhL._AC_SL1500_.jpg", "description": "Samsung 55-inch Crystal UHD 4K Smart TV", "price": 89999, "sku": "UHD_4K", "product_id": 3},
    {"id": 14, "image1": "https://m.media-amazon.com/images/I/81889-KTklL._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/81FedcV6cgL._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/71I8PuXGSCL._AC_SL1500_.jpg", "description": "Sony X900H 65-inch 4K LED TV", "price": 79999, "sku": "Sony_4K", "product_id": 20},
    {"id": 15, "image1": "https://m.media-amazon.com/images/I/41AuMsehblL._SX300_SY300_QL70_FMwebp_.jpg", "image2": "https://m.media-amazon.com/images/I/51BJqgjkNRL._SX522_.jpg", "image3": "https://m.media-amazon.com/images/I/51-7uvU4PML._SL1080_.jpg", "description": "PlayStation 5 Console", "price": 44999, "sku": "PlayStation_5", "product_id": 4},
    {"id": 16, "image1": "https://m.media-amazon.com/images/I/71OVwWVSjTL.__AC_SX300_SY300_QL70_FMwebp_.jpg", "image2": "https://m.media-amazon.com/images/I/71CAWOSdHcS._AC_SX679_.jpg", "image3": "https://m.media-amazon.com/images/I/81AOl+lvyNS._AC_SX679_.jpg", "description": "PlayStation VR Headset", "price": 49999, "sku": "VR_PS5", "product_id": 26},
    {"id": 17, "image1": "https://m.media-amazon.com/images/I/71c+YOxfTGL._AC_SL1500_.jpg", "image2": "https://m.media-amazon.com/images/I/712MaLSweAL._AC_SL1500_.jpg", "image3": "https://m.media-amazon.com/images/I/717ywH7EvcL._AC_SL1500_.jpg", "description": "Logitech G Pro Headset", "price": 5999, "sku": "HSet_pro", "product_id": 27}
    # Add the remaining variants here...
]

# Bulk indexing function
def bulk_index(index_name, documents):
    actions = [{"_index": index_name, "_id": doc["id"], "_source": doc} for doc in documents]
    bulk(es, actions)
    print(f"Data successfully indexed into '{index_name}'!")

# Index data into respective indices
bulk_index(index_category, categories)
bulk_index(index_product, products)
bulk_index(index_product_variant, product_variants)
