#include "DHT.h"
#include <Servo.h>
#define DHTPIN 2
#define DHTTYPE DHT11

Servo sg90;
const int toprakSensor = A0;  // 
const int yagmur = A1;  // Mavi
int isk = A5;
int suSeviye = A2; // 


const int havmot= 4;
const int buzz = 8;
const int led = 5;
//const int havalandirma = 4;
const int isitma = 3;
const int sulama = 7;
const int nemMot = 6;


DHT dht(DHTPIN, DHTTYPE);

char incomingData; // Variable to store incoming data from Bluetooth module


void setup() { 
  pinMode(led,OUTPUT);
  pinMode(havmot,OUTPUT);
  pinMode(buzz,OUTPUT);
 // pinMode(havalandirma,OUTPUT);
  pinMode(isitma,OUTPUT);
  pinMode(sulama,OUTPUT);
  pinMode(nemMot,OUTPUT);
  pinMode(isk,INPUT);
  pinMode(toprakSensor,INPUT);
  pinMode(suSeviye,INPUT);
  dht.begin();
  
  Serial.begin(9600);
}

void loop() {

  int isik = analogRead(isk);
  int toprak = analogRead(toprakSensor);
  int suseviyesi = analogRead(suSeviye);
  float h = dht.readHumidity();
  float t = dht.readTemperature();
    
  Serial.println("SuSeviyesi");
  Serial.println(suseviyesi);
  Serial.println("Isik");
  Serial.println(isik);
  Serial.println("Hava nem");
  Serial.println(h);
  Serial.println("sicaklik");
  Serial.println(t);
  Serial.println("torak nem");
  Serial.println(toprak);

  delay(1000);

  //eğer toprak nemli ise bunu yap
  if(toprak>700){           
    digitalWrite(sulama, HIGH);
    delay(100);                           
    digitalWrite(sulama,LOW);
  }
  
  //işik var ise led off
  if(isik>200){
    digitalWrite(led,LOW);
  }

  //işik yok ise led on
  if(isik<200){
   digitalWrite(led,HIGH);
  }

  //eğer Humidity yüksekise havalandırma on
  if(h>50){
    digitalWrite(havmot,HIGH);
    delay(400);
    digitalWrite(havmot,LOW);
  }

  //eğer humaniti düşük ise nemlendir
  if(h<40){
    digitalWrite(nemMot,HIGH);
    delay(500);
    digitalWrite(nemMot,LOW);
  }

  //eğer sıcaklık fazla isehavalandır
  if(t>25){
    digitalWrite(havmot,HIGH);
    delay(400);
    digitalWrite(havmot,LOW);
  }

  //sıcaklık az ise isit
  if(t<23){
    digitalWrite(isitma,LOW);
    delay(500);
    digitalWrite(isitma,HIGH);
  }
 
  
  //su seviyesi tam dolu ise buzzerri off
  if(suseviyesi>600){
    digitalWrite(buzz,LOW);
  }
  //su seviyesi arafta iken buzzer flip flop yap
  if(suseviyesi<650){
    if(suseviyesi>500){
    digitalWrite(buzz,HIGH);
    delay(50);
    digitalWrite(buzz,LOW); 
  }}
  //su seviyesi bitmiş ise buzzer on
  if(suseviyesi<500){
    digitalWrite(buzz,HIGH);
  }
  delay(3000);
}
