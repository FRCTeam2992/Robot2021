#include <Adafruit_NeoPixel.h>

// Spindexer Lights
#define SPINDEXER_PIN 6
#define SPINDEXER_COUNT 99
#define SPINDEXER_REAL_COUNT 203

// Spindexer Lights
Adafruit_NeoPixel spindexerStrip = Adafruit_NeoPixel(SPINDEXER_REAL_COUNT, SPINDEXER_PIN, NEO_GRB + NEO_KHZ800);

// Color Codes
int white = spindexerStrip.Color(255, 255, 255);
int blue = spindexerStrip.Color(0, 0, 255);
int red = spindexerStrip.Color(255, 0, 0);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
}

void loop() {
  coolRainbowThing();
}

void blueChase() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);

    if (i == 0) {
      setSpindexerPixel(SPINDEXER_COUNT - 10, white);
    } else if (i == 1) {
      setSpindexerPixel(SPINDEXER_COUNT - 9, white);
    } else if (i == 2) {
      setSpindexerPixel(SPINDEXER_COUNT - 8, white);
    } else if (i == 3) {
      setSpindexerPixel(SPINDEXER_COUNT - 7, white);
    } else if (i == 4) {
      setSpindexerPixel(SPINDEXER_COUNT - 6, white);
    } else if (i == 5) {
      setSpindexerPixel(SPINDEXER_COUNT - 5, white);
    } else if (i == 6) {
      setSpindexerPixel(SPINDEXER_COUNT - 4, white);
    } else if (i == 7) {
      setSpindexerPixel(SPINDEXER_COUNT - 3, white);
    } else if (i == 8) {
      setSpindexerPixel(SPINDEXER_COUNT - 2, white);
    } else if (i == 9) {
      setSpindexerPixel(SPINDEXER_COUNT - 1, white);
    } else if (i == 10) {
      setSpindexerPixel(SPINDEXER_COUNT, white);
    }

    spindexerStrip.show();
    delay(10);
  }
}
void alternatingBlueWhiteBounce() {
  bool wasBlue = false;

  for (int i = 0; i < SPINDEXER_COUNT; i += 2) {

    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, blue);
      wasBlue = true;
    }
    spindexerStrip.show();
    delay(20);
  }
  wasBlue = true;
  for (int i = 96; i >= 0; i -= 2) {
    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, blue);
      wasBlue = true;
    }
    spindexerStrip.show();
    delay(20);
  }
}

void coolRainbowThing() {

  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, (0, 0, 0));
    setSpindexerPixel(i - 1, red);
  }
  spindexerStrip.show();
  delay(20);
}

void setSpindexerPixel(int index, int color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
