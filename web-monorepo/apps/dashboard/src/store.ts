import {create} from 'zustand';

type state = {
    count: number;
    increment: () => void;
    decrement: () => void;
    reset: () => void;
}
//we created a custom hook useStore here 
export const useStore = create<state>((set) => ({
    count: 0,
    increment: ()=> set((state)=> ({count: state.count + 1}) ),
    decrement: ()=> set((state)=> ({count: state.count - 1}) ),
    reset: ()=> set(()=> ({count: 0}) )

}));