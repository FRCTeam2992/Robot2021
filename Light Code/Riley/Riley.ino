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
uint32_t moreBlue = spindexerStrip.Color(0, 0, 203);
uint32_t purple = spindexerStrip.Color(255, 0, 255);
uint32_t white = spindexerStrip.Color(255, 255, 255);
uint32_t black = spindexerStrip.Color(0, 0, 0);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

void loop() {
  rileyCode();
  //alternatingColorBounce();
}
// bounce color switch
void rileyCode() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i - 10, blue);

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
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
  for (int i = 0; i <= 98; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);

    spindexerStrip.show();
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
}        // old stuff
//(int i = 0; i < SPINDEXER_COUNT; i += 2

// alternateing color space bounce
void alternatingColorBounce() {
  bool wasBlue = false;

  for  (int i = 0; i < SPINDEXER_COUNT; i += 8) {
    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, moreBlue);
      wasBlue = true;
    }
    spindexerStrip.show();
    delay(20);
  }   
  wasBlue = true;
  for (int i = 96; i >= 0; i -= 8) {
    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, moreBlue);
      wasBlue = true;
    }
    for (int i = 96; i >= 0; i -= 8) {
      spindexerStrip.show();
      delay(0);
    }
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
