import axiosClient from "./axios";
import configApi from './config';

export const category = {
    profile: 'profile',
    club: 'club',
}

// export const movieType = {
//     upcoming: 'upcoming',
//     popular: 'popular',
//     top_rated: 'top_rated'
// }

// export const tvType = {
//     popular: 'popular',
//     top_rated: 'top_rated',
//     on_the_air: 'on_the_air'
// }

const filterApi = {

    getProfilesList: (cate, params) => {
        const url = category[cate] ;
        return axiosClient.get(url, params);
    },

    detail: (cate, id, params) => {
        const url = category[cate] + '/' + id;
        return axiosClient.get(url, params);
    },

    download: (cate, id, params) => {
        const url = category[cate] + '/' + id +'/image/download';   
        return configApi.baseUrl+url;           // cannot resolve base64
    }
}

export default filterApi;