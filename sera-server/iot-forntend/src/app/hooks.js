import {useDispatch, useSelector} from 'react-redux';

// import them from the source and use everywhere
export const useAppDispatch = () => useDispatch();
export const useAppSelector = useSelector;
