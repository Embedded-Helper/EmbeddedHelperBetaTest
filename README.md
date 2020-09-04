# Embedded Helper Beta Test
[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Try%20out%20the%20new%20Arduino%20Library%20Extension%20for%20the%20Arduino%20IDE%20by%20Embedded%20Helper&url=https://embeddedhelper.com/&hashtags=automation,InternetOfThings) [![Slack](https://img.shields.io/badge/Slack-2-lightgrey)](https://join.slack.com/t/embeddedhelper/shared_invite/zt-g8vjxeti-TmMyLruscyZziFPmT9DzJw)

[![Price](https://img.shields.io/badge/price-FREE-0098f7.svg)](https://github.com/froala/design-blocks/blob/master/LICENSE)
[![GitHub package version](https://img.shields.io/badge/version-v1.0.0-blue)](https://github.com/Embedded-Helper/EmbeddedHelperBetaTest)

Extension to the Arduino IDE that automatically generates Arduino Classes.

<p><a href="http://embeddedhelper.com/">Embedded Helper Website (Web Demo Coming Soon) »</a></p>

### Step 1:
## Table of contents

- [Quick start](#quick-start)
- [Dependencies](#dependencies)

## Quick start

1. **Download Froala Design Blocks.** There are several ways to start using the Froala Design Blocks depending on how you prefer:

- [Use the builder](https://www.froala.com/design-blocks/webpage-builder)

- [Download the latest release](https://github.com/froala/design-blocks/blob/master/froala-design-blocks.zip?raw=true) and then read the [What's included](#whats-included) section below.

- Clone the repo and run it.

  ```bash
  git clone https://github.com/froala/design-blocks.git
  cd design-blocks
  npm install
  npm run start
  ```

2. **Design Blocks Skeleton.** You can use the following code layout as a starting point.

   ```html
   <!DOCTYPE html>
   <html>
     <head>
       <title>Froala Design Blocks - Skeleton</title>
       <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0">
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
       <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
       <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/froala-design-blocks/2.0.1/css/froala_blocks.min.css">
     </head>
   
     <body>
         <!-- Insert HTML for contents. -->
     </body>
   </html>    
   ```

3. **Add design blocks.** Once you have the Froala Design Blocks basic HTML structure in place, start browsing the design blocks that you want to use and copy/paste the HTML for them.

## Dependencies

- **Bootstrap**. Froala Design Blocks is built on Bootstrap 4 library and fully supports it. It uses the Javascript files only for the header design blocks, so if you don't need them, we recommend not to include the Bootstrap JS files in order to reduce your bundle size.

- **Font Awesome**. We're using the amazing Font Awesome library for the social network icons.

- **Google Fonts**. By default, the Design Blocks toolkit is built using the Roboto font, however that can easily be changed to other fonts.
