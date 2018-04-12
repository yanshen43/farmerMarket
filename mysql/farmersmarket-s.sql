# -------------------------------------------------------------
# SQL Script to Create All Seven Tables for FarmersMarket
# -------------------------------------------------------------

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS goodslist;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS farm;
DROP TABLE IF EXISTS photo;
DROP TABLE IF EXISTS customer;

/*Farm table lists details from USDA Farmer's Market API: 
https://www.ams.usda.gov/local-food-directories/farmersmarkets
*/
CREATE TABLE farm(
  FMID        VARCHAR(8)  NOT NULL PRIMARY KEY, 
  MarketName  VARCHAR(64) NOT NULL,
  Website     VARCHAR(64),
  Facebook    VARCHAR(128),
  street      VARCHAR(64) NOT NULL,
  city        VARCHAR(16) NOT NULL,
  State       VARCHAR(16) NOT NULL,
  zip         VARCHAR(8) NOT NULL,
  Season1Date VARCHAR(32), 
  Season1Time VARCHAR(256),
  Season2Date VARCHAR(32),
  Season2Time VARCHAR(64),
  Season3Date VARCHAR(32),
  Season3Time VARCHAR(64),
  x           NUMERIC(11,7) NOT NULL,
  y           NUMERIC(11,7) NOT NULL,
  Credit      VARCHAR(1),
  WIC         VARCHAR(1),
  WICcash     VARCHAR(1),
  SFMNP       VARCHAR(1),
  SNAP        VARCHAR(1),
  Organic     VARCHAR(1),
  Rating      NUMERIC(4,1)
);
INSERT INTO farm(FMID,MarketName,Website,Facebook,street,city,State,zip,Season1Date,Season1Time,Season2Date,Season2Time,x,y,Credit,WIC,WICcash,SFMNP,SNAP,Organic,Rating) VALUES 
(1000254,'Blacksburg Farmers Market','http://www.blacksburgfarmersmarket.com','https://www.facebook.com/blacksburgfarmersmarket','108 W Roanoke St, Suite 101','Blacksburg','Virginia','24060','05/01 to 10/31','Wed: 2:00 PM-7:00 PM;Sat: 8:00 AM-2:00 PM;','11/01 to 12/31','Wed: 2:00 PM-6:00 PM;Sat: 9:00 AM-2:00 PM;',-80.414503,37.229248,'Y','N','N','N','Y','Y',0),
(1009853,'Community Market of Blacksburg','http://www.thecommunityfarmersmarket.com','https://www.facebook.com/TCFMB','1400 Block South Main Street','Blacksburg','Virginia','24060','05/17 to 12/20','Tue: 9:00 AM-2:00 PM;Sat: 8:00 AM-2:00 PM;',NULL,NULL,-80.400238,37.215805,'Y','N','N','N','Y','-',0),
(1011161,'Christiansburg Farmers Market','http://www.christiansburg.org/farmersmarket','http://www.facebook.com/ChristiansburgFarmersMarket','West Main Street and Hickok Street','Christiansburg','Virginia','24073','05/07 to 10/15','Thu: 3:00 PM-7:00 PM;',NULL,NULL,-80.410187,37.12877,'Y','N','N','N','Y','-',0),
(1006733,'Shawsville Farmers'' Market','http://www.ShawsvilleFarmersMarket.webs.com','https://www.facebook.com/pages/Shawsville-Farmers-Market/387248851313781','267 Alleghany Spring Rd.','Shawsville','Virginia','24162','05/07 to 10/01','Sat: 9:00 AM-12:00 PM;',NULL,NULL,-80.2558,37.167,'Y','N','N','N','N','-',0),
(1011853,'Radford Farmers'' Market','http://www.radfordchamber.com','https://www.facebook.com/rfmkt','East Main Street','Radford','Virginia','24141','05/14  to 10/15','Sat: 8:00 AM-1:00 PM;',NULL,NULL,-80.557664,37.141092,'Y','N','N','N','N','-',0),
(1010957,'Pearisburg Community Market','http://www.pearisburgfarmtofork.org','https://www.facebook.com/pearisburgcommunitymarket','1410 Wenonah Avenue','Pearisburg','Virginia','24134','05/02 to 10/31','Thu: 4:00 PM-7:00 PM;Sat: 9:00 AM-2:00 PM;',NULL,NULL,-80.719213,37.326041,'Y','N','N','N','Y','-',0),
(1011496,'Catawba Valley Farmers'' Market',NULL,'Catawba Valley Farmers Market','4965 Catawba Creek Road','Catawba','Virginia','24070','05/07 to 10/15','Thu: 3:30 PM-7:00 PM;',NULL,NULL,-80.1064449,37.383009,'Y','N','N','N','Y','N',0),
(1005214,'Salem Farmers Market','http://market.salemva.gov',NULL,'3 East Main Street','Salem','Virginia','24153','04/05 to 12/20','Tue: 8:00 AM-5:00 PM;Wed: 8:00 AM-5:00 PM;Thu: 8:00 AM-5:00 PM;Fri: 8:00 AM-5:00 PM;Sat: 7:00 AM-2:00 PM;','01/04 to 03/29','Sat: 9:00 AM-12:00 PM;',-80.058606,37.292997,'Y','N','N','N','Y','-',0),
(1012590,'Floyd Farmers Market','http://sustainfloyd.org','floydfarmersmarket','203 South Locust Street','Floyd','Virginia','24091','05/14 to 11/19','Sat: 9:00 AM-1:00 PM;',NULL,NULL,-80.319761,36.91065,'Y','N','N','N','Y','Y',0),
(1012771,'THE MARKETPLACE','http://pulaskimarketplace.com/','www.facebook.com/pulaskimarketplace','20 S Washinton Ave','Pulaski','Virginia','24301','05/17 to 09/06','Tue: 4:00 PM-8:00 PM;',NULL,NULL,-80.7792887,37.0458736,'Y','N','N','N','N','-',0),
(1008429,'THE MARKETPLACE',NULL,'https://www.facebook.com/pages/Pulaski-County-Chamber-of-Commerce/102867005722','20 South Washington Avenue','Pulaski','Virginia','24301','05/07 to 09/24','Tue: 4:00 PM-8:00 PM;',NULL,NULL,-80.7800281,37.0453871,'Y','N','N','Y','N','-',0),
(1002597,'Draper Valley Farmer''s Market',NULL,NULL,'3054 Greenbriar Road','Draper','Virginia','24324','05/01 to 10/31',NULL,NULL,NULL,-80.7422,37.0009,'N','N','N','N','N','-',0),
(1010640,'Grandin Village Community Market','http://www.leapforlocalfood.org/markets/grandin-market','www.facebook.com/grandinvillagecommunitymarket','2080 Westover Ave SW','Roanoke','Virginia','24015','04/25 to 10/31','Sat: 8:00 AM-12:00 PM;','11/19 to 12/31',NULL,-79.978011,37.264859,'Y','N','N','N','Y','Y',0),
(1010641,'West End Community Market','http://www.leapforlocalfood.org/markets/west-end-market','www.facebook.com/westendcommunitymarket','1210 Patterson Ave SW','Roanoke','Virginia','24016','01/01 to 12/31','Tue: 3:00 PM-6:00 PM;',NULL,NULL,-79.962639,37.271991,'Y','N','N','N','Y','Y',0),
(1008128,'Monroe Farm Market',NULL,'https://www.facebook.com/monroefarmmarket','Monroe Health Center Drive','Union','West Virginia','24983','01/01 to 12/31','Tue: -12:00 PM;Sun: 5:00 PM-;',NULL,NULL,-80.547387,37.5883119,'Y','N','N','N','N','Y',0),
(1003951,'Vinton Farmers Market','http://www.vintonva.gov','https://www.facebook/vinton farmers'' market','204 W. Lee Ave.','Vinton','Virginia','24179','05/01 to 12/09','Mon: 7:00 AM-7:00 PM;Tue: 7:00 AM-7:00 PM;Wed: 7:00 AM-7:00 PM;Thu: 7:00 AM-7:00 PM;Fri: 7:00 AM-7:00 PM;Sat: 7:00 AM-7:00 PM;Sun: 7:00 AM-7:00 PM;',NULL,NULL,-79.898891,37.2803308,'Y','N','N','N','Y','-',0),
(1011174,'Botetourt Farmer''s Market','http://www.facebook.com/botetourtfarmersmarket','www.facebook.com/botetourtfarmersmarket','Daleville Town Center','Daleville','Virginia','24083','05/07 to 10/15','Sat: 9:00 AM-1:00 PM;',NULL,NULL,-79.913375,37.417409,'Y','N','N','N','N','N',0),
(1008252,'Alderson Farmers Market','http://www.aldersonmarket.org','https://www.facebook.com/aldersonfood','Rt. 3  Main Street','Alderson','West Virginia','24910','05/01 to 10/31','Sat: 8:00 AM-12:30 PM;',NULL,NULL,-80.642024,37.725954,'Y','N','N','Y','Y','Y',0),
(1000991,'Ronceverte Farmers Market',NULL,NULL,'1ST National Bank Employees Parking Lot','Ronceverte','West Virginia','24970','05/01 to 10/31','Tue:9:00 am - 1:00 pm;',NULL,NULL,-80.4612,37.7737,'N','N','N','N','N','-',0),
(1005560,'Southwest Virginia Farmers'' Market','http://www.swvafarmersmarket.org/','https://www.facebook.com/swvafarmersmarket','497 Farmers Market Drive','Hillsville','Virginia','24343','01/01 to 12/31','Mon: 8:00 AM-5:00 PM;Tue: 8:00 AM-5:00 PM;Wed: 8:00 AM-5:00 PM;Thu: 8:00 AM-5:00 PM;Fri: 8:00 AM-5:00 PM;Sat: 8:00 AM-5:00 PM;Sun: 8:00 AM-5:00 PM;',NULL,NULL,-80.772739,36.742039,'Y','N','N','Y','N','N',0),
(1004270,'Lewisburg Farmer''s Market','http://www.lewisburgfarmersmarket.com','https://www.facebook.com/LewisburgFarmersMarket','214 E. Washington St.','Lewisburg','West Virginia','24901','04/01 to 10/29','Sat: 8:30 AM-1:00 PM;',NULL,NULL,-80.443128,37.800849,'Y','Y','N','Y','Y','N',0),
(1002609,'Bland County Farmers Market','http://www.bland.org','https://www.facebook.com/pages/Bland-County-Farmers-Market/105110259527804','591 Main Street','Bland','Virginia','24315','06/01 to 10/25','Sat: 9:00 AM-12:00 PM;',NULL,NULL,-81.115074,37.100871,'N','N','N','N','N','-',0),
(1006634,'Wytheville Farmers Market','http://www.wythevillefarmersmarket.com','https://www.facebook.com/wytheville.farmersmarket','355 East Main Street','Wytheville','Virginia','24382','05/04 to 10/26','Sat: 9:00 AM-1:00 PM;',NULL,NULL,-81.080771,36.951632,'Y','N','N','Y','Y','-',0),
(1001052,'Stuart Farmers Market','http://www.townofstuartva.com','https://www.facebook.com/pages/Stuart-Farmers-Market/113677132003596','320 Chestnut Street','Stuart','Virginia','24171','05/01 to 10/31','Tue: 3:00 PM-6:00 PM;Fri: 7:00 AM-12:00 PM;',NULL,NULL,-80.26824,36.63798,'Y','N','N','N','Y','Y',0),
(1001981,'Covington Farmers Market','http://www.covingtonfarmersmarket.com',NULL,'134 West Main Street','Covington','Virginia','24426','05/18 to 10/19','Sat: 8:00 AM-1:00 PM;',NULL,NULL,-79.99436,37.79351,'Y','N','N','N','N','-',0),
(1005436,'Bluefield VA Farmers Market',NULL,NULL,'Walnut Street','Bluefield','Virginia','24605','05/01 to 10/31','Fri: 8:00 AM-12:00 PM;',NULL,NULL,-81.27162,37.251613,'N','N','N','Y','Y','N',0),
(1010802,'Farmers'' Market at the Y','http://bedfordymca.org/FARMERS-MARKET-AT-THE-Y.htm','https://www.facebook.com/farmersmarketattheY?ref=bookmarks','Bedord Area Family YMCA 1111 Turnpike Road, Bedford, VA','Bedford','Virginia','24523','04/07 to 10/27','Tue: 3:00 PM-6:30 PM;','11/17 to 12/31','Tue: 2:30 PM-5:00 PM;',-79.561497,37.33813,'Y','N','N','N','N','-',0),
(1002815,'Uptown Martinsville Farmers'' Market','http://www.martinsvilleuptown.com','https://www.facebook.com/groups/48152046033/','65 W Main St','Martinsville','Virginia','24112','04/26 to 10/25','Sat: 7:00 AM-12:00 PM;',NULL,NULL,-79.8745407,36.69208,'Y','N','N','Y','Y','N',0),
(1009000,'City of Galax Farmers Market','http://www.galaxfarmersmarket.com','https://www.facebook.com/pages/Galax-Farmers-Market/436987119716502','Main Street and Washington Street','Galax','Virginia','24333','05/17 to 10/04','Fri: 8:00 AM-1:00 PM;Sat: 8:00 AM-12:00 PM;',NULL,NULL,-80.925092,36.663427,'N','N','N','N','N','-',0),
(1010175,'Bedford Farmers Market','http://bedfordymca.org/BEDFORD%20FARMERS%20MARKET.htm','https://www.facebook.com/BedfordFarmersMarket/','Corner of Washington and Center streets','Bedford','Virginia','24523','05/01 to 10/01','Tue: 3:00 PM-6:00 PM;Fri: 8:00 AM-1:30 PM;',NULL,NULL,-79.5252117,37.3337321,'Y','N','N','N','Y','N',0),
(1008830,'Bramwell Farmers'' Market',NULL,'https://www.facebook.com/BramwellFarmersMarket','100 simmons ave','Bramwell','West Virginia','24715','06/05 to 10/09','Fri: 9:00 AM-1:00 PM;',NULL,NULL,-81.313467,37.325145,'Y','Y','N','Y','N','-',0);

/*Category table is the relation between farmer's market and goods categories */
CREATE TABLE category(
   CID      INT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
   FMID     VARCHAR(8)  NOT NULL,
   CName    VARCHAR(16) NOT NULL,
   Icon     VARCHAR(32)
);
INSERT INTO category(FMID,Cname,Icon) VALUES 
(1000254,'Bakedgoods',NULL),(1000254,'Eggs',NULL),(1000254,'Vegetables',NULL),(1000254,'Honey',NULL),(1000254,'Jams',NULL),
(1000254,'Meat',NULL),(1000254,'Fruits',NULL),(1000254,'Grains',NULL),(1009853,'Bakedgoods',NULL),(1009853,'Cheese',NULL),
(1009853,'Eggs',NULL),(1009853,'Vegetables',NULL),(1009853,'Honey',NULL),(1009853,'Jams',NULL),(1009853,'Meat',NULL),
(1009853,'Fruits',NULL),(1009853,'Juices',NULL),(1011161,'Bakedgoods',NULL),(1011161,'Eggs',NULL),(1011161,'Vegetables',NULL),
(1011161,'Honey',NULL),(1011161,'Jams',NULL),(1011161,'Meat',NULL),(1011161,'Fruits',NULL),(1011161,'Juices',NULL),
(1006733,'Bakedgoods',NULL),(1006733,'Eggs',NULL),(1006733,'Vegetables',NULL),(1006733,'Fruits',NULL),(1011853,'Bakedgoods',NULL),
(1011853,'Eggs',NULL),(1011853,'Vegetables',NULL),(1011853,'Fruits',NULL),(1010957,'Bakedgoods',NULL),(1010957,'Eggs',NULL),
(1010957,'Vegetables',NULL),(1010957,'Honey',NULL),(1010957,'Jams',NULL),(1010957,'Meat',NULL),(1010957,'Fruits',NULL),
(1011496,'Bakedgoods',NULL),(1011496,'Eggs',NULL),(1011496,'Vegetables',NULL),(1011496,'Honey',NULL),(1011496,'Jams',NULL),
(1011496,'Meat',NULL),(1011496,'Fruits',NULL),(1005214,'Bakedgoods',NULL),(1005214,'Cheese',NULL),(1005214,'Eggs',NULL),
(1005214,'Vegetables',NULL),(1005214,'Honey',NULL),(1005214,'Jams',NULL),(1005214,'Meat',NULL),(1005214,'Fruits',NULL),
(1012590,'Bakedgoods',NULL),(1012590,'Cheese',NULL),(1012590,'Eggs',NULL),(1012590,'Vegetables',NULL),(1012590,'Honey',NULL),
(1012590,'Jams',NULL),(1012590,'Meat',NULL),(1012590,'Fruits',NULL),(1012590,'Grains',NULL),(1012590,'Juices',NULL),
(1012771,'Bakedgoods',NULL),(1012771,'Eggs',NULL),(1012771,'Vegetables',NULL),(1012771,'Honey',NULL),(1012771,'Jams',NULL),
(1012771,'Meat',NULL),(1012771,'Fruits',NULL),(1008429,'Bakedgoods',NULL),(1008429,'Eggs',NULL),(1008429,'Vegetables',NULL),
(1008429,'Honey',NULL),(1008429,'Jams',NULL),(1008429,'Meat',NULL),(1008429,'Fruits',NULL),(1002597,'Bakedgoods',NULL),
(1002597,'Cheese',NULL),(1002597,'Vegetables',NULL),(1002597,'Jams',NULL),(1002597,'Meat',NULL),(1010640,'Bakedgoods',NULL),
(1010640,'Cheese',NULL),(1010640,'Eggs',NULL),(1010640,'Vegetables',NULL),(1010640,'Honey',NULL),(1010640,'Jams',NULL),
(1010640,'Meat',NULL),(1010640,'Fruits',NULL),(1010640,'Grains',NULL),(1010640,'Juices',NULL),(1010641,'Bakedgoods',NULL),
(1010641,'Cheese',NULL),(1010641,'Eggs',NULL),(1010641,'Vegetables',NULL),(1010641,'Honey',NULL),(1010641,'Jams',NULL),
(1010641,'Meat',NULL),(1010641,'Fruits',NULL),(1010641,'Grains',NULL),(1010641,'Juices',NULL),(1008128,'Bakedgoods',NULL),
(1008128,'Eggs',NULL),(1008128,'Vegetables',NULL),(1008128,'Honey',NULL),(1008128,'Jams',NULL),(1008128,'Fruits',NULL),
(1008128,'Grains',NULL),(1003951,'Bakedgoods',NULL),(1003951,'Eggs',NULL),(1003951,'Vegetables',NULL),(1003951,'Honey',NULL),
(1003951,'Jams',NULL),(1003951,'Meat',NULL),(1003951,'Fruits',NULL),(1011174,'Bakedgoods',NULL),(1011174,'Cheese',NULL),
(1011174,'Eggs',NULL),(1011174,'Vegetables',NULL),(1011174,'Honey',NULL),(1011174,'Jams',NULL),(1011174,'Meat',NULL),
(1011174,'Fruits',NULL),(1011174,'Grains',NULL),(1008252,'Bakedgoods',NULL),(1008252,'Vegetables',NULL),(1008252,'Jams',NULL),
(1008252,'Meat',NULL),(1008252,'Fruits',NULL),(1000991,'Honey',NULL),(1000991,'Jams',NULL),(1005560,'Bakedgoods',NULL),
(1005560,'Cheese',NULL),(1005560,'Eggs',NULL),(1005560,'Vegetables',NULL),(1005560,'Honey',NULL),(1005560,'Jams',NULL),
(1005560,'Fruits',NULL),(1005560,'Grains',NULL),(1004270,'Bakedgoods',NULL),(1004270,'Eggs',NULL),(1004270,'Vegetables',NULL),
(1004270,'Honey',NULL),(1004270,'Jams',NULL),(1004270,'Meat',NULL),(1004270,'Fruits',NULL),(1002609,'Bakedgoods',NULL),
(1002609,'Eggs',NULL),(1002609,'Vegetables',NULL),(1002609,'Honey',NULL),(1006634,'Bakedgoods',NULL),(1006634,'Eggs',NULL),
(1006634,'Vegetables',NULL),(1006634,'Honey',NULL),(1006634,'Jams',NULL),(1006634,'Meat',NULL),(1006634,'Fruits',NULL),
(1001052,'Bakedgoods',NULL),(1001052,'Eggs',NULL),(1001052,'Vegetables',NULL),(1001052,'Honey',NULL),(1001052,'Jams',NULL),
(1001052,'Meat',NULL),(1001052,'Fruits',NULL),(1001052,'Juices',NULL),(1001981,'Bakedgoods',NULL),(1001981,'Cheese',NULL),
(1001981,'Eggs',NULL),(1001981,'Vegetables',NULL),(1001981,'Jams',NULL),(1001981,'Fruits',NULL),(1005436,'Bakedgoods',NULL),
(1005436,'Eggs',NULL),(1005436,'Vegetables',NULL),(1005436,'Honey',NULL),(1005436,'Jams',NULL),(1005436,'Meat',NULL),
(1005436,'Fruits',NULL),(1010802,'Bakedgoods',NULL),(1010802,'Eggs',NULL),(1010802,'Vegetables',NULL),(1010802,'Jams',NULL),
(1010802,'Meat',NULL),(1010802,'Fruits',NULL),(1002815,'Cheese',NULL),(1002815,'Vegetables',NULL),(1002815,'Honey',NULL),
(1002815,'Jams',NULL),(1002815,'Fruits',NULL),(1009000,'Bakedgoods',NULL),(1009000,'Eggs',NULL),(1009000,'Vegetables',NULL),
(1009000,'Honey',NULL),(1009000,'Jams',NULL),(1009000,'Fruits',NULL),(1010175,'Bakedgoods',NULL),(1010175,'Eggs',NULL),
(1010175,'Vegetables',NULL),(1010175,'Honey',NULL),(1010175,'Jams',NULL),(1010175,'Meat',NULL),(1010175,'Fruits',NULL),
(1010175,'Grains',NULL),(1010175,'Juices',NULL),(1008830,'Bakedgoods',NULL),(1008830,'Vegetables',NULL),(1008830,'Jams',NULL),
(1008830,'Fruits',NULL);

/*Goods table lists details for a specific farm product*/
CREATE TABLE goods(
   GID      INT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
   GName    VARCHAR(32) NOT NULL,
   Image    VARCHAR(8)  NOT NULL,
   Price    NUMERIC(5,2)  NOT NULL,
   Unit     VARCHAR(16) NOT NULL,
   Category VARCHAR(16) NOT NULL
);
INSERT INTO goods(GName,Image,Price,Unit,Category) VALUES 
('Cinnamon Raisin Bread',1,4,'22 oz','Bakedgoods'),
('Blueberry Lemon Scone Bites',2,7,'20 bites/box','Bakedgoods'),
('Chocolate Chip Cookies',3,8,'15 bites/box','Bakedgoods'),
('Maple Leaf Smoked Gouda',4,9.99,'lb','Cheese'),
('Shropshire Blue',5,14.99,'lb','Cheese'),
('Pasture Raised Eggs',6,3.99,'dozen','Eggs'),
('Medium Free Range Chicken Eggs',7,3.5,'dozen','Eggs'),
('Jumbo Brown Coturnix Quail Eggs',8,4.5,'18 ct','Eggs'),
('Organic Kale',9,3.49,'bunch','Vegetables'),
('Radishes',10,3,'lb','Vegetables'),
('Butterhead Lettuce',11,3.5,'lb','Vegetables'),
('Acorn Squash',12,4,'lb','Vegetables'),
('Sweet Potatoes- Purple',13,3.5,'lb','Vegetables'),
('Raw Comb Honey',14,7,'4 oz','Honey'),
('Raw Chunk Honey',15,9,'lb','Honey'),
('Preserves Fig',16,7,'12 oz','Jams'),
('Ground Pork Burger Patties',17,5.99,'4 pack','Meat'),
('Ground Beef',18,6.99,'lb','Meat'),
('Double Smoked Sweet Bacon',19,7.99,'lb','Meat'),
('Pasture Raised Pulled Pork',20,7.5,'lb','Meat'),
('Sirloin Steak',21,9.99,'lb','Meat'),
('Florida Citrus Oranges',22,3,'lb','Fruits'),
('Persimmons',23,3,'2 ct','Fruits'),
('Gala Apples',24,3.5,'5 ct','Fruits'),
('Granola-Butter Pecan',25,9,'8 oz','Grains'),
('Orange Juice',26,6,'32 oz','Juices');

/*GoodsList Table join Farm, Category and Goods Table and lists information for a product*/
CREATE TABLE goodslist( 
    GLID        INT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FMID        VARCHAR(8) NOT NULL,
    MarketName  VARCHAR(64) NOT NULL,
    Website     VARCHAR(64),
    Category    VARCHAR(16) NOT NULL,
    GID         INT UNSIGNED  NOT NULL,    
    GName       VARCHAR(32) NOT NULL, 
    Image       VARCHAR(8)  NOT NULL,
    Price       NUMERIC(5,2) NOT NULL,
    Unit        VARCHAR(16) NOT NULL
);
INSERT INTO goodslist(FMID, MarketName, Website, Category, GID, GName, Image, Price, Unit)
SELECT farm.FMID, MarketName, Website, category.CName, GID, GName,Image, Price, Unit  
FROM farm 
INNER JOIN category On farm.FMID = category.FMID
INNER JOIN goods On category.CName = goods.Category; 

/* The Cart table contains the goods and quantity in customer's shopping cart. */
CREATE TABLE cart(
   CartID   INT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
   GLID     INT UNSIGNED  NOT NULL,
   GName    VARCHAR(32) NOT NULL,
   Image    VARCHAR(8)  NOT NULL,
   Price    NUMERIC(5,2)  NOT NULL,
   Unit     VARCHAR(16) NOT NULL,
   Category VARCHAR(16) NOT NULL,
   Quantity INT NOT NULL
);

/* The Customer table contains attributes of interest of a customer. */
CREATE TABLE customer
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR (32) NOT NULL,
    password VARCHAR (32) NOT NULL,
    first_name VARCHAR (32) NOT NULL,
    middle_name VARCHAR (32),
    last_name VARCHAR (32) NOT NULL,
    cc_number VARCHAR (19) NOT NULL, /* Credit Card Number, max 19 digits */
    cc_expires VARCHAR (4) NOT NULL, /* Expiration date in MMYY format, e.g., 0523 meaning May 2023 */
    cc_security_code VARCHAR (4) NOT NULL, /* American Express 4 digits; other credit cards 3 digits */
    address1 VARCHAR (128) NOT NULL,
    address2 VARCHAR (128),
    city VARCHAR (64) NOT NULL,
    state VARCHAR (2) NOT NULL,
    zipcode VARCHAR (10) NOT NULL, /* e.g., 24060-1804 */
    security_question INT NOT NULL, /* Refers to the number of the selected security question */
    security_answer VARCHAR (128) NOT NULL,
    email VARCHAR (128) NOT NULL,      
    PRIMARY KEY (id)
);

/* The Photo table contains attributes of interest of a customer's photo. */
CREATE TABLE photo
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    extension ENUM('jpeg', 'jpg', 'png') NOT NULL,
    customer_id INT UNSIGNED,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);
