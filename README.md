# Embedded Helper Beta Test
[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Try%20out%20the%20new%20Arduino%20Library%20Extension%20for%20the%20Arduino%20IDE%20by%20Embedded%20Helper&url=https://embeddedhelper.com/&hashtags=automation,InternetOfThings) [![Slack](https://img.shields.io/badge/Slack-2-lightgrey)](https://join.slack.com/t/embeddedhelper/shared_invite/zt-g8vjxeti-TmMyLruscyZziFPmT9DzJw)

[![Price](https://img.shields.io/badge/price-FREE-0098f7.svg)](https://github.com/froala/design-blocks/blob/master/LICENSE)
[![GitHub package version](https://img.shields.io/badge/version-v1.0.0-blue)](https://github.com/Embedded-Helper/EmbeddedHelperBetaTest)

Extension to the Arduino IDE that automatically generates Arduino Classes.

<p><a href="http://embeddedhelper.com/">Embedded Helper Website (Web Demo Coming Soon) »</a></p>


## Quick start

1. **Download Arduino IDE.** This is software commonly used to program Arduino and other similar chips

      [Download Link](https://www.arduino.cc/en/main/software)

2. **Download Latest Release of this Project** In the form of two .jar files

      [Download Link](https://github.com/Embedded-Helper/EmbeddedHelperBetaTest/releases/latest)


3. **Add Jar Files to Arduino tools folder.** How to install the EmbeddedHelper Extension

  In your Arduino installation folder (usually in `Program Files (x86)/Arduino` or `Documents`), create a folder called `EmbeddedHelper` in the tools folder, then another folder in `EmbeddedHelper` called `tool`; the folders should look like `Arduino/tools/Embeddedhelper/tool/`. Copy the downloaded files from step 2 into the final `tool` folder
  
  The result will look similar to this (On Windows 10):
      ![Image of folder structure](images/toolsDir.PNG)
      
4. **Launch Arduino IDE** To see the extension

   Now launch the Arduino Software, and the Embedded Helper tool will show up in the `Tools` menu as `Generate Class
    
   ![Showing installed extension](images/installedExtension.PNG)

   **[And please fill out our beta test feedback form to share your thoughts!](https://forms.gle/oQyqJfXD3KEXB9Zq7)**


## Examples

​	Please see the Examples folder for example Arduino Sketches to convert to libraries

## Special Features

The tool also includes special features designed to make writing Arduino libraries more efficient

#### Input author, organization, and supported boards

- Type Author, Organization, and Boards Supported in this format in your sketch header comment
- ![Timer Example Header Comment](images\TimerHeaderComment.PNG)
- And the relevant fields will be added to your generated library.properties file
- ![Timer Library Properties](images\TimerLibraryProperties.PNG)
- and the header comment will be reformatted in your generated header, body, and example files

- ![Timer Example Header Comment](images\TimerExampleHeaderComment.PNG)

- If you specified supported boards, your library will only compile if those boards are used, see the generated .cpp file and .h file

- Generate class using the Timer example in the examples folder to see these features demonstrated!

  #### Publishing Library to Arduino Library Manager

  This tool makes it easy to publish your library to the [Arduino Library Manager](https://github.com/arduino/Arduino/wiki/Library-Manager-FAQ)

  1. Make sure your library compiles
  2.  Delete the .development folder, this allows you to modify the example file while writing the library but is not allowed in a library submission
  3. Update the fields in the library.properties file with a sentence describing the library and other relevant fields
  4. Create a github repository for your library
  5. "[Tag](https://git-scm.com/book/en/v2/Git-Basics-Tagging) it and push the tag (or create a release if you web hosting offers a way to do it, for example with [GitHub "releases"](https://help.github.com/articles/creating-releases/))
  6. Open an issue on [Arduino's GitHub](https://github.com/arduino/Arduino/issues), specifying the URL of the repository from where to download your library. If you have multiple libraries to submit you are welcome to do them all in a single issue." From [Library Manager FAQ](https://github.com/arduino/Arduino/wiki/Library-Manager-FAQ)

 