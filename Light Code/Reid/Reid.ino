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
uint32_t blue = spindexerStrip.Color(0, 0, 255);
uint32_t purple = spindexerStrip.Color(255, 0, 255);

uint32_t cyan = spindexerStrip.Color(0, 255, 200);
uint32_t mint = spindexerStrip.Color(50, 255, 50);
uint32_t lime = spindexerStrip.Color(75, 255, 4);
uint32_t babyish_blue = spindexerStrip.Color(85, 85, 255);
uint32_t pink = spindexerStrip.Color(255, 60, 60);
uint32_t magenta = spindexerStrip.Color(255, 0, 155);
uint32_t turquoise = spindexerStrip.Color(0, 214, 179);

uint32_t black = spindexerStrip.Color(0, 0, 0);
uint32_t white = spindexerStrip.Color(255, 255, 255);

uint32_t rPurple = spindexerStrip.Color(85, 0, 220);
uint32_t rPink = spindexerStrip.Color(252, 3, 115);
uint32_t rBlue = spindexerStrip.Color(0, 67, 156);
uint32_t rGreenBlue = spindexerStrip.Color(0, 255, 157);
uint32_t rMint = spindexerStrip.Color(94, 255, 143);
uint32_t rLime = spindexerStrip.Color(0, 255, 64);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

void loop() {
  //Dissolve();
  SectionDissolve();
}

void Dissolve() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    int randomPixel = random(0, SPINDEXER_COUNT);

    while (spindexerStrip.getPixelColor(randomPixel) == white) {
      randomPixel = random (0, SPINDEXER_COUNT);
    }

    setSpindexerPixel(randomPixel, white);
    spindexerStrip.show();
    delay(30);
  }

  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    int randomPixel = random(0, SPINDEXER_COUNT);

    while (spindexerStrip.getPixelColor(randomPixel) == blue) {
      randomPixel = random (0, SPINDEXER_COUNT);
    }

    setSpindexerPixel(randomPixel, blue);
    spindexerStrip.show();
    delay(30);
  }
}

void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}

void SectionDissolve() {
  //Middle Section
  for (int i; i > SPINDEXER_COUNT / 3; i++) {
    int randomPixel = random((SPINDEXER_COUNT / 2) - SPINDEXER_COUNT / 6, (SPINDEXER_COUNT / 2) + SPINDEXER_COUNT / 6);

    while (spindexerStrip.getPixelColor(randomPixel) == white) {
      randomPixel = random ((SPINDEXER_COUNT / 2) - SPINDEXER_COUNT / 6, (SPINDEXER_COUNT / 2) + SPINDEXER_COUNT / 6);
    }

    setSpindexerPixel(randomPixel, white);
    spindexerStrip.show();
    delay(30);
  }
 
}
