// 랜덤 수 가져오기
function getRandomInt(max) {
  let randomNum = Math.floor(Math.random() * max);
  return randomNum;
}

// 강아지 리스트의 품종 가져오기
async function getDogList() {
  const url = 'https://dog.ceo/api/breeds/list/all';

  const response = await fetch(url);
  const data = await response.json();
  const dogList = data.message;
  const arrDogList = Object.keys(dogList);

  return arrDogList;
}

// 품종 랜덤 url 계산하기
function randomListUrl(arrDogList) {
  let randomNum = getRandomInt(100);
  let dogListOne = arrDogList[randomNum];

  let url = `https://dog.ceo/api/breed/${dogListOne}/images/random`;
  return url;
}

// 강아지 사진 url data 가져오기
async function getDogImgUrl(url) {
  const response = await fetch(url);
  const data = await response.json();
  const dogImgUrl = data.message;

  return dogImgUrl;
}

async function main() {
  const arrDogList = await getDogList();
  let url = randomListUrl(arrDogList);
  let dogImgUrl = await getDogImgUrl(url);
  const dogImgTag = document.querySelector('.dog');
  const dogBtn = document.querySelector('.dog-btn');

  dogImgTag.setAttribute('src', dogImgUrl);

  dogBtn.addEventListener('click', () => {
    let url = randomListUrl(arrDogList);

    fetch(url)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        return data.message;
      })
      .then((imgUrl) => {
        dogImgTag.setAttribute('src', imgUrl);
      })
      .catch((error) => {
        console.error(error);
      });
  });
}

main();
