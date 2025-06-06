import config from '../../apikey';
// 현재 상영 중인 영화 중 평점이 가장 높은 영화

// 현재 상영 중인 영화 리스트
async function getMovies() {
  const baseURL = 'https://api.themoviedb.org/3/movie';
  const path = '/now_playing';
  const params = new URLSearchParams({
    api_key: config.TMDB_API_KEY,
  });

  const URL = `${baseURL}${path}?${params}`;
  const response = await fetch(URL);
  const data = await response.json();
  const movies = data.results;

  return movies;
}

// 가장 평점 높은 영화
function maxPopularMovie(movies) {
  let maxVoteAverage = 0;
  let maxVoteMovie = null;

  for (let movie of movies) {
    let curVoteAverage = movie.vote_average;
    if (maxVoteAverage < curVoteAverage) {
      maxVoteAverage = curVoteAverage;
      maxVoteMovie = movie;
    }
  }

  return maxVoteMovie;
}

// 가장 평점 높은 영화 - reduce
function maxPopularMovie2(movies) {
  let popularMovie = movies.reduce((acc, cur) => {
    let accVoteAverage = acc.vote_average;
    let curVoteAverage = cur.vote_average;

    return curVoteAverage > accVoteAverage ? cur : acc;
  });
  return popularMovie;
}

async function main() {
  const movies = await getMovies();
  const popularMovie = maxPopularMovie2(movies);

  // 가장 평점 높은 영화의 포스터 주소
  const imgBaseUrl = 'https://image.tmdb.org/t/p/w500/';
  const imgPath = popularMovie.poster_path;
  const imgUrl = `${imgBaseUrl}${imgPath}`;
  console.log(imgUrl);
}

main();
