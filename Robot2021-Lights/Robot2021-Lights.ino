#include <Adafruit_NeoPixel.h>

// Spindexer Lights
#define SPINDEXER_PIN 6
#define SPINDEXER_COUNT 99
#define SPINDEXER_REAL_COUNT 203

// Spindexer Lights
Adafruit_NeoPixel spindexerStrip = Adafruit_NeoPixel(SPINDEXER_REAL_COUNT, SPINDEXER_PIN, NEO_GRB + NEO_KHZ800);

// Color Codes
int red = spindexerStrip.Color(255, 0, 0);
int orange = spindexerStrip.Color(255, 128, 0);
int yellow = spindexerStrip.Color(255, 175, 0);
int green = spindexerStrip.Color(255, 110, 0);
int blue = spindexerStrip.Color(0, 0, 255);
int purple = spindexerStrip.Color(155, 0, 1);

int black = spindexerStrip.Color(0, 0, 0);
int white = spindexerStrip.Color(255, 255, 255);
int cyan = spindexerStrip.Color(165, 42, 42);


void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  setStripWhite();
}

void loop() {
  reidsBounceCode();
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
    for (int i = 96; i >= 0; i -= 2) {
      spindexerStrip.show();
      delay(20);
    }
  }
}
void setStripWhite() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
  }
  for (int i = 0; i < 10; i++) {
    setSpindexerPixel(i, blue);
  }
  spindexerStrip.show();
}

void reidsBounceCode() {
  for (int i = 10; i < 99; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);

    spindexerStrip.show();
    delay(15);
  }

  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i + 10, white);

    spindexerStrip.show();
    delay(15);
  }
}





void setSpindexerPixel(int index, int color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
