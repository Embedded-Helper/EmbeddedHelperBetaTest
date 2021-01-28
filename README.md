# Embedded Helper Beta Test
[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Try%20out%20the%20new%20Arduino%20Library%20Extension%20for%20the%20Arduino%20IDE%20by%20Embedded%20Helper&url=https://embeddedhelper.com/&hashtags=automation,InternetOfThings) [![Slack](https://img.shields.io/badge/Slack-2-lightgrey)](https://join.slack.com/t/embeddedhelper/shared_invite/zt-g8vjxeti-TmMyLruscyZziFPmT9DzJw)

[![Price](https://img.shields.io/badge/price-FREE-0098f7.svg)](https://github.com/froala/design-blocks/blob/master/LICENSE)
[![GitHub package version](https://img.shields.io/badge/version-v1.0.3-blue)](https://github.com/Embedded-Helper/EmbeddedHelperBetaTest)

Extension to the Arduino IDE that automatically generates Arduino Classes.

<p><a href="http://embeddedhelper.com/">Embedded Helper Website (Web Demo Coming Soon) »</a></p>


## Quick start

1. **Download Arduino IDE.** This is software commonly used to program Arduino and other similar chips

      [Download Link](https://www.arduino.cc/en/main/software)

2. **Download Latest Release of this Project** In the form of two .jar files

      [Download Link](https://github.com/Embedded-Helper/EmbeddedHelperBetaTest/releases/latest)
  

3. **Add Jar Files to Arduino tools folder.** Sets on how to install the EmbeddedHelper Extension

  In your Arduino installation folder (`usually in Program Files (x86)/Arduino`), create a new tools folder
  in the tools folder called `EmbeddedHelper`, then make another folder in it called `tool`; the folders should look like `tools/Embeddedhelper/tool/`. Copy the downloaded files from step 2 in that final tool folder
  
  The result will look similar to this (On Windows 10):
      ![Image of folder structure](images/toolsDir.PNG)
  
4. **Launch Arduino IDE** Start using EmbeddedHelper
  
  Launch the Arduino IDE, and the Embedded Helper tool will show up in the `Tools` menu as `Generate Class`
      ![Showing installed extension](images/installedExtension.PNG)
