## <mark color="#fbc956">Scraping</mark>

> **파일 위치**
>
> - **crawling**\_scraping
>   - html_scraping.js
>   - browser_scraping.js

1. `npm install axios`
2. `npm install jsdom`

### 1. htmlscraping

- `node html_scraping.js`

```jsx
const axios = require("axios");
const { JSDOM } = require("jsdom");

const URL = "https://news.google.com/home?hl=ko&gl=KR&ceid=KR:ko";

async function scrapingPractice() {
  const response = await axios(URL);

  const data = response.data;

  const dom = new JSDOM(data);

  const document = dom.window.document;

  // article 모두 선택
  const articles = document.querySelectorAll("article");
  // article 태그들 반복을 돌면서
  articles.forEach((article) => {
    // 그 안의 a 태그를 찾아
    const aTags = article.querySelectorAll("a");
    // 그들의 text 출력
    aTags.forEach((aTag) => console.log(aTag.textContent));
  });
}

scrapingPractice();
```

### 2. browser_scraping

1. `npm install puppeteer`

- `node browser_scraping.js`

```jsx
const { default: puppeteer } = require("puppeteer");

async function scrapingPractice() {
  const URL = "https://comic.naver.com/webtoon?tab=mon";

  const browswer = await puppeteer.launch({
    headless: false,
  });

  const page = await browswer.newPage();

  await page.goto(URL);

  const text = await page.$eval("h1", (el) => el.textContent);
  // document.querySelector('h1').textContent
  console.log(text);

  const imgUrls = await page.$$eval(
    "#content > div:nth-child(1) > ul img",
    (imgs) => {
      console.log(imgs);
      imgs.map((img) => img.src);
    }
  );
  console.log(imgUrls);
}

scrapingPractice();
```
