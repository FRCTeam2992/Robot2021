#include <Adafruit_NeoPixel.h>

// Spindexer Lights
#define SPINDEXER_PIN 6
#define SPINDEXER_COUNT 99
#define SPINDEXER_REAL_COUNT 203

// Spindexer Lights
Adafruit_NeoPixel spindexerStrip = Adafruit_NeoPixel(SPINDEXER_REAL_COUNT, SPINDEXER_PIN, NEO_GRB + NEO_KHZ800);

// Color Codes
uint32_t red = spindexerStrip.Color(255, 0, 0);
uint32_t orange = spindexerStrip.Color(255, 50, 0);
uint32_t yellow = spindexerStrip.Color(255, 130, 0);
uint32_t green = spindexerStrip.Color(0, 255, 0);
uint32_t blue = spindexerStrip.Color(0, 0, 255);
uint32_t purple = spindexerStrip.Color(255, 0, 255);

uint32_t black = spindexerStrip.Color(0, 0, 0);
uint32_t white = spindexerStrip.Color(255, 255, 255);
uint32_t cyan = spindexerStrip.Color(0, 255, 200);
uint32_t mint = spindexerStrip.Color(50, 255, 50);
uint32_t lime = spindexerStrip.Color(75, 255, 4);
uint32_t babyish_blue = spindexerStrip.Color(85, 85, 255);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

void loop() {
  //reidsBounceCode();
  //alternatingBlueWhiteBounce;
  //blueChase();
  //CJCode();
  //CJCodeButGood();
  ColorTest();
}


void ColorTest() {
  for (int i = 1; i <= 99; i++) {
    setSpindexerPixel(i, mint);
    spindexerStrip.show();
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


int n = 76;
int f = 10;

void reidsBounceCode() {

  for (int i = 10; i < 99; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);
    spindexerStrip.show();
    delay(n);
  }
  n -= f;

  for (int i = 89; i >= 0; i--) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i + 10, white);
    spindexerStrip.show();
    delay(n);
  }
  if (n <= 6) {
    n = 6;
    f = -10;
  }

  if (n >= 76) {
    n = 76;
    f = 10;
  }
  n -= f;
}

void blueChase() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i - 10, blue);

    if (i == 0) {
      setSpindexerPixel(SPINDEXER_COUNT - 10, blue);
    } else if (i == 1) {
      setSpindexerPixel(SPINDEXER_COUNT - 9, blue);
    } else if (i == 2) {
      setSpindexerPixel(SPINDEXER_COUNT - 8, blue);
    } else if (i == 3) {
      setSpindexerPixel(SPINDEXER_COUNT - 7, blue);
    } else if (i == 4) {
      setSpindexerPixel(SPINDEXER_COUNT - 6, blue);
    } else if (i == 5) {
      setSpindexerPixel(SPINDEXER_COUNT - 5, blue);
    } else if (i == 6) {
      setSpindexerPixel(SPINDEXER_COUNT - 4, blue);
    } else if (i == 7) {
      setSpindexerPixel(SPINDEXER_COUNT - 3, blue);
    } else if (i == 8) {
      setSpindexerPixel(SPINDEXER_COUNT - 2, blue);
    } else if (i == 9) {
      setSpindexerPixel(SPINDEXER_COUNT - 1, blue);
    } else if (i == 10) {
      setSpindexerPixel(SPINDEXER_COUNT, blue);
    }

    spindexerStrip.show();
    delay(10);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, blue);

    spindexerStrip.show();
    delay(15);
  }
}

int a = 0;
int speed = 20;

void CJCode() {
  for (int d = 0; d >= 0; d++) {
    for (int i = 0; i < SPINDEXER_COUNT; i++) {
      setSpindexerPixel(i, blue);
      setSpindexerPixel(i - 10, white);
      if (i % 98 == 0) {
        a++;
        speed += 10;
      }
      else if (a > 3) {
        i = 100;
      }
      spindexerStrip.show();
      delay(speed);
    }
    a = 0;
    for (int i = 88; i >= 0; i--) {
      setSpindexerPixel(i, blue);
      setSpindexerPixel(i + 10, white);

      if (i % 98 == 0) {
        a++;
        speed -= 10;
      }
      else if (a > 3) {
        i = 100;
      }
      spindexerStrip.show();
      delay(speed);
    }
    for (int i = 0; i <= 3; i++) {
      for (int i = 0; i < SPINDEXER_COUNT; i++) {
        setSpindexerPixel(i, white);
      }
      spindexerStrip.show();
      delay (20);
      for (int i = 0; i < 10; i++) {
        setSpindexerPixel(i, blue);
      }
      spindexerStrip.show();
    }
  }
}

void CJCodeButGood() {
  int n = 75;
  for (int i = 10; i < 99; i++) {
    n += .5;
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);

    spindexerStrip.show();
    delay(15);
  }

  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i + 10, white);

    spindexerStrip.show();
    delay(n);
  }
  if (n < 1) {
    int n = 75;
  }
}



void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  //spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  //spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
