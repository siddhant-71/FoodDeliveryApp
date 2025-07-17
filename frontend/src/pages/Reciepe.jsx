import ReactMarkdown from 'react-markdown';
import "./ArtificialIntelligence.css"
import { useEffect, useState } from 'react';
function RecipeComponent({response}) {
    const [displayedText, setDisplayedText] = useState('');
    const [wordIndex, setWordIndex] = useState(0);

    const words = response.split(' ');

    useEffect(() => {
        if (wordIndex < words.length) {
        const timeout = setTimeout(() => {
            setDisplayedText(prev => prev + words[wordIndex] + ' ');
            setWordIndex(wordIndex + 1);
        }, 100); // delay between words (ms)

        return () => clearTimeout(timeout);
        }
    }, [wordIndex,words]);
  return (
    <div className="recipe-box">
      <ReactMarkdown>{response}</ReactMarkdown>
    </div>
  );
}
export default RecipeComponent