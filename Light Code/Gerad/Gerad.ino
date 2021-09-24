#include <Adafruit_NeoPixel.h>


// Spindexer Lights
#define SPINDEXER_PIN 6
#define SPINDEXER_COUNT 72
#define SPINDEXER_REAL_COUNT 145

// Spindexer Lights
Adafruit_NeoPixel spindexerStrip = Adafruit_NeoPixel(SPINDEXER_REAL_COUNT, SPINDEXER_PIN, NEO_GRB + NEO_KHZ800);

// Color Codes
uint32_t red = spindexerStrip.Color(255, 0, 0);
uint32_t orange = spindexerStrip.Color(255, 50, 0);
uint32_t yellow = spindexerStrip.Color(255, 160, 0);
uint32_t green = spindexerStrip.Color(0, 255, 0);
uint32_t blue = spindexerStrip.Color(0, 29, 220);
uint32_t purple = spindexerStrip.Color(255, 0, 255);
uint32_t white = spindexerStrip.Color(253, 208, 150);
uint32_t black = spindexerStrip.Color(0, 0, 0);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

int i = 0;
int aa = 0;
int ii = 72;

int q = 0;
int t = 0;
int w = 0;

int y = 0;
int u = 0;
int o = 0;

void loop() {
  //GoodLights();
  //Test();
  //ColorMixing();
  BlueWhiteSpin();
}

void BlueWhiteSpin(){
for (int i = 0; i < 20; i++){
  setSpindexerPixel(i, 0, 29, 220);
  setSpindexerPixel(i-10, 253, 208, 150);
  setSpindexerPixel(i+10, 253, 208, 150);
  setSpindexerPixel(i+20, 0, 29, 220);
  setSpindexerPixel(i+30, 253, 208, 150);
  setSpindexerPixel(i+40, 0, 29, 220);
  setSpindexerPixel(i+50, 253, 208, 150);

   if (i < 13){
    setSpindexerPixel(i+60, 0, 29, 220);
   }
  delay (23);
  spindexerStrip.show();
  }
}

void Test() {
  setSpindexerPixel(45, 255, 255, 255);
  spindexerStrip.show();
}

int aRed = 255;
int aGreen = 0;
int aBlue = 0;

int bRed = 0;
int bGreen = 0;
int bBlue = 255;

int mixedRed = 0;
int mixedGreen = 0;
int mixedBlue = 0;

int randomColorA = 0;
int randomColorB = 0;

int bb = 0;
int cc = 0;
int dd = 0;

void ColorMixing() {
  randomColorA = random(3);
  randomColorB = random(3);
  
  if (randomColorA == 0) {
    aRed = random(100, 256);
    aGreen = random(50);
    aBlue = random(50);
  }

  if (randomColorA == 1) {
    aRed = random(50);
    aGreen = random(100, 256);
    aBlue = random(50);
  }

  if (randomColorA == 2) {
    aRed = random(50);
    aGreen = random(50);
    aBlue = random(100, 256);
  }

  if (randomColorB == 0) {
    bRed = random(100, 256);
    bGreen = random(50);
    bBlue = random(50);
  }

  if (randomColorB == 1) {
    bRed = random(50);
    bGreen = random(100, 256);
    bBlue = random(50);
  }

  if (randomColorB == 2) {
    bRed = random(50);
    bGreen = random(50);
    bBlue = random(100, 256);
  }

  mixedRed = (aRed + bRed) / 2;
  mixedGreen = (aGreen + bGreen) / 2;
  mixedBlue = (aBlue + bBlue) / 2;

  for (int i = 0; i < 38; i++) {
    aa = 72 - i;
    setSpindexerPixel(i, aRed, aGreen, aBlue);
    setSpindexerPixel(i - 10, 253, 208, 150);

    setSpindexerPixel(aa, bRed, bGreen, bBlue);

    if (aa < 62) {
      setSpindexerPixel(aa + 10, 253, 208, 150);
    }
    delay(30);
    spindexerStrip.show();
  }
  
for (int i = 36; i < 42; i++){
    bb = ii - i;
    setSpindexerPixel(i, mixedRed, mixedGreen, mixedBlue);
    setSpindexerPixel(i - 10, white);
    
    setSpindexerPixel(bb, mixedRed, mixedGreen, mixedBlue);
    setSpindexerPixel(bb + 10, white);
    delay(30);
    spindexerStrip.show();
  }

  for (int i = 41; i < 73; i++){
    cc = ii - i;
    
    setSpindexerPixel(i, aRed, aGreen, aBlue);
    
    if (i < 46){
      setSpindexerPixel(i - 11, bRed, bGreen, bBlue);
      setSpindexerPixel(cc + 10, aRed, aGreen, aBlue);
    }
    
    if (i > 46) {
    setSpindexerPixel(i - 10, 253, 208, 150);
    }
    
    setSpindexerPixel(cc, bRed, bGreen, bBlue);
    if (i > 45) {
     setSpindexerPixel(cc +10, 253, 208, 150);
    }

    delay(30);
    spindexerStrip.show();
  }
  
for (int i = 62; i < 72; i++){
  dd = 72 - i; 
  setSpindexerPixel(i, white);
  setSpindexerPixel(dd, white);
  
  delay(30);
  spindexerStrip.show();
    }
  }




void GoodLights() {
  for (int i = 0; i < 37; i++) {
    aa = ii - i;
    setSpindexerPixel(i, 75, 75, 255);
    setSpindexerPixel(i - 18, 253, 208, 150);

    setSpindexerPixel(aa, 75, 75, 255);

    if (aa < 54) {
      setSpindexerPixel(aa + 18, 253, 208, 150);
    }
    delay(35);
    spindexerStrip.show();
  }
  for (int t = 1; t < 4; t++) {
    w = 75 - t * 19;
    for (int q = 36; q < 73; q++) {
      u = 72 - q;
      setSpindexerPixel(q, w, w, 255);
      setSpindexerPixel(u, w, w, 255);

      delay(30);
      spindexerStrip.show();
    }
  }
  for (int y = 0; y < 36; y++) {
    setSpindexerPixel(y, 253, 208, 150);
    setSpindexerPixel(y - 9, 5, 5, 255);

    setSpindexerPixel(71 - y, 253, 208, 150);
    setSpindexerPixel(81 - y, 5, 5, 255);
    delay(30);
    spindexerStrip.show();
  }
  for (int y = 0; y < 27; y++) {
    setSpindexerPixel(y, 253, 208, 150);
    setSpindexerPixel(y - 9, 5, 5, 255);

    setSpindexerPixel(71 - y, 253, 208, 150);
    setSpindexerPixel(81 - y, 5, 5, 255);
    delay(30);
    spindexerStrip.show();
  }
  for (int y = 0; y < 18; y++) {
    setSpindexerPixel(y, 253, 208, 150);
    setSpindexerPixel(y - 9, 5, 5, 255);

    setSpindexerPixel(71 - y, 253, 208, 150);
    setSpindexerPixel(81 - y, 5, 5, 255);
    delay(30);
    spindexerStrip.show();
  }
  for (int y = 0; y < 9; y++) {
    setSpindexerPixel(y, 253, 208, 150);


    setSpindexerPixel(71 - y, 253, 208, 150);
    delay(30);
    spindexerStrip.show();
  }
  delay(1000);
}

void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
